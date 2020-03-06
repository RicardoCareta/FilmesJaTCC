using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace programaInstaladorTCC2
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();

           // DAOFIle d = new DAOFIle();
          //  d.GetFile(1);
        }

        private void chkTermos_CheckedChanged(object sender, EventArgs e)
        {
            //Isso não é uma gambiarra, somente uma forma mais rapida de fazer
            btnInstalar.Enabled = btnCancelar.Enabled = chkTermos.Checked;
        }

        private void btnCancelar_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void btnInstalar_Click(object sender, EventArgs e)
        {
            frmInstalando f = new frmInstalando();
            this.Visible = false;
            f.ShowDialog();
            this.Close();
        }
    }
}
