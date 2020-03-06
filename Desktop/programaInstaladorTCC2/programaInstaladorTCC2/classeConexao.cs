using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Oracle.DataAccess.Client;

namespace programaInstaladorTCC2
{
    public class classeConexao
    {
        private OracleConnection cn;

        private const string CONNECTION_STRING = "Data Source = (DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521))(CONNECT_DATA = (SERVICE_NAME = xe)));User Id=TCC;Password=123456;";
        public OracleConnection getConnection()
        {
            return cn;
        }

        public void Conectar()
        {
            cn = new OracleConnection(CONNECTION_STRING);
            if (cn.State == System.Data.ConnectionState.Closed)
            {
                cn.Open();
            }
        }

        public void Fechar()
        {
            if (cn.State == System.Data.ConnectionState.Open)
            {
                cn.Close();
            }
        }

    }
}
