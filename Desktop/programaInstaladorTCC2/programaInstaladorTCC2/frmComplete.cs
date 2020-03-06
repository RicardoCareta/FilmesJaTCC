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
    public partial class frmComplete : Form
    {
        public frmComplete()
        {
            InitializeComponent();
        }

        private void btnFinalizar_Click(object sender, EventArgs e)
        {
            if (chkComplete.Checked)
            {
                ExecuteProgram();
            }

            Application.Exit();
        }

        #region MMethods
        private void ExecuteProgram()
        {
            //Programar para abrir o programa, com cmd mesmo, ou algo do tipo
        }
        #endregion
    }
}
