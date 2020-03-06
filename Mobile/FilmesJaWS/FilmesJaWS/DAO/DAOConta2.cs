using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

using System.Json;
using Oracle.DataAccess.Client;
using Oracle.DataAccess.Types;

namespace FilmesJaWS.DAO
{
    public class DAOConta
    {
        private OracleCommand cmd;
        private OracleDataReader dr;
        private util.ClasseConexao cn;

        public DAOConta()
        {
            cn = new util.ClasseConexao();
        }

        public string insertConta(string Nome, string DataNascimento, int IDUsuario, int IdIdioma, string ImageString)
        {
            byte[] image = Convert.FromBase64String(ImageString);

            cn.Conectar();
            string query = "insert into tblConta(Nome, Foto, DTNASCIMENTO, IDCLiente, IDIDIOMA, ATIVO, LINE) values('" + Nome + "',:PICT,TO_DATE('" + DataNascimento + "','dd/mm/yyyy')," + IDUsuario + "," + IdIdioma + ", 1, 0)";
            cmd = new OracleCommand(query, cn.getConnection());


            OracleParameter blobParameter = new OracleParameter();
            blobParameter.OracleDbType = OracleDbType.Blob;
            blobParameter.ParameterName = "PICT"; 
            blobParameter.Value = image; 

            cmd.Parameters.Add(blobParameter); 

            string retorno = "1";
            try
            {
                cmd.ExecuteNonQuery();
            }
            catch (Exception ex)
            {
                retorno = ex.Message + "\n" + query;
            }

            cn.Desconectar();
            return retorno;
        }

        public string DeletarConta(int id)
        {
            string retorno = "1";
            cn.Conectar();
            cmd = new OracleCommand("update tblConta set ATIVO = 0 where IDCONTA = " + id, cn.getConnection());
            try
            {
                cmd.ExecuteNonQuery();
            }
            catch (Exception ex)
            {
                retorno = ex.Message;
            }

            cn.Desconectar();

            return retorno;
        }
    }
}