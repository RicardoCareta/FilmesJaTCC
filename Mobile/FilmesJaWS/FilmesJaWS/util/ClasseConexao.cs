using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

using Oracle.DataAccess.Client;

namespace FilmesJaWS.util
{ 
    public class ClasseConexao
    {
        private const string CONNECTION_STRING = "Data Source = (DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521))(CONNECT_DATA = (SERVICE_NAME = xe)));User Id=TCC;Password=123456;";

        private OracleConnection cn;

        public ClasseConexao()
        {
            cn = new OracleConnection(CONNECTION_STRING);
        }

        public OracleConnection getConnection()
        {
            return cn;
        }

        public void Conectar()
        {
            try
            {
                if (cn.State == System.Data.ConnectionState.Closed)
                {
                    cn.Open();
                }
            }
            catch (Exception)
            {
                throw;
            }
        }

        public void Desconectar()
        {
            try
            {
                if (cn.State == System.Data.ConnectionState.Open)
                {
                    cn.Close();
                }
            }
            catch (Exception)
            {
                throw;
            }
        }
    }
}