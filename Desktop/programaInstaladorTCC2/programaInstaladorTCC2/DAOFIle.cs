using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Oracle.DataAccess.Client;

namespace programaInstaladorTCC2
{
    public class DAOFIle
    {
        private OracleCommand cmd;
        private OracleDataReader dr;
        private classeConexao cn;

        public List<MDFiles> files;

        public DAOFIle()
        {
            cn = new classeConexao();
            files = Selecionar();
        }

        public byte[] GetFile(int id)
        {
            foreach (MDFiles f in files)
            {
                if (f.ID == id)
                {
                    return f.File;
                }
            }

            return null;
        }

        public List<MDFiles> Selecionar()
        {
            List<MDFiles> files = new List<MDFiles>();
            cn.Conectar();
            cmd = new OracleCommand("select * from TBLFiles", cn.getConnection());
            dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                MDFiles file = new MDFiles();
                file.ID = (int)dr.GetOracleDecimal(0);
                file.File = (byte[])dr[1];
                file.FileName = dr.GetString(2);

                files.Add(file);
            }

            cn.Fechar();

            return files;
        }
    }
}
