using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace programaInstaladorTCC2
{
    public partial class frmInstalando : Form
    {
        private bool instaledFinish = false;
        private bool filesFinish = false;
        private frmInstalando instalado;

        private string pathImages = "";
        private string mainFolder = "";
        private string pathProps = "";

        private int countFiles = 0;

        private DAOFIle daoFile;

        public frmInstalando()
        {
            InitializeComponent();
            daoFile = new DAOFIle();
            Instalador();
            Thread t = new Thread(new ThreadStart(ThreadProgressBar));
            t.Start();
            instalado = this;
            
        }

        private void btnCancelar_Click(object sender, EventArgs e)
        {
            try
            {
                progressBar1.Value += 13;
            }
            catch (ArgumentOutOfRangeException)
            {
                progressBar1.Value = 100;
            }
            
            CancelConfig();
            //this.Close();
        }

        #region AutoGenerateCodeByM
        private void CancelConfig()
        {
            //Programar para deletar a pasta criada
            var fileName = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.ApplicationData), "FilmesJa");
            if (File.Exists(fileName))
            {
                File.Delete(fileName);
            }
        }

        private void Instalador()
        {
            CreateFolders();
            Thread t = new Thread(new ThreadStart(CreateAllFiles));
            t.Start();
        }

        private void CreateFolders()
        {
            var fileName = mainFolder = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.ApplicationData), "FilmesJa");
            DirectoryInfo di = Directory.CreateDirectory(fileName);

            pathImages = Path.Combine(fileName, "Images");
            pathProps = Path.Combine(fileName, "Props");

            Directory.CreateDirectory(pathImages);
            Directory.CreateDirectory(pathProps);
        }

        private void CreateAllFiles()
        {
            foreach (MDFiles file in daoFile.Selecionar())
            {
                /*Thread t = new Thread(() => CreateFile(mainFolder + file.FileName, file.File));
                t.Start();*/
                CreateFile(mainFolder + file.FileName, file.File);
            }

            /*while (daoFile.files.Count - 1 > countFiles)
            {

            }*/

            File.Create(pathProps + "/Config");

            filesFinish = true;

            string path = Environment.GetFolderPath(Environment.SpecialFolder.Desktop);
            string shortcutLocation = System.IO.Path.Combine(path, "FilmesJa.lnk");
            IWshRuntimeLibrary.WshShell shell = new IWshRuntimeLibrary.WshShell();
            IWshRuntimeLibrary.IWshShortcut shortcut = (IWshRuntimeLibrary.IWshShortcut)shell.CreateShortcut(shortcutLocation);

            shortcut.Description = "FilmesJaShortcut";  
            //shortcut.IconLocation = @"c:\myicon.ico";         
            shortcut.TargetPath = System.IO.Path.Combine(mainFolder, "FilmesJa.exe"); 

            shortcut.Save();

            this.SetProgressValue(100);

        }

        private void CreateFile(string path, byte[] file)
        {
            File.WriteAllBytes(path, file);
            countFiles++;
            SetProgressValue(progressBar1.Value + 1);
        }

        #endregion


        delegate void SetValueProgressCallBack(int value);
        private void SetProgressValue(int value)
        {
            if (progressBar1.InvokeRequired)
            {
                SetValueProgressCallBack d = new SetValueProgressCallBack(SetProgressValue);
                this.Invoke(d, new object[] { value });
            }
            else
            {
                progressBar1.Value = value;
            }
        }

        private void ThreadProgressBar()
        {
            while (true)
            {
                if (progressBar1.Value >= 100 && filesFinish)
                {
                    instaledFinish = true;
                    break;
                }
                
            }
            Thread.Sleep(1000);

            if (instaledFinish)
            {
                frmComplete c = new frmComplete();
                //instalado.Visible = false;
                c.ShowDialog();
                //instalado.Close();
            }
        }
    }
}
