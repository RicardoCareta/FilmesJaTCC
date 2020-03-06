using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

using System.Json;

using Oracle.DataAccess.Client;
namespace FilmesJaWS.DAO
{
    public class DAOCatalogo
    {
        private OracleCommand cmd;
        private OracleDataReader dr;
        private util.ClasseConexao cn;

        public DAOCatalogo()
        {
            cn = new util.ClasseConexao();
        }

        public string getTopFilmes()
        {
            string ret = "";
            dynamic listJson = new JsonArray();

            cn.Conectar();
            cmd = new OracleCommand("select * from TopMidia t inner join TBLCatalogo c on t.IDCat = c.IDCat and c.IDserieCatalogo = 0 and c.ativo = 1 where rownum <= 10", cn.getConnection());
            dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                string retorno = "";
                dynamic catalogo = new JsonObject();
                byte[] k = null;
                if (dr.GetOracleBlob(3).Value != null)
                {
                    k = dr.GetOracleBlob(3).Value;
                }

                catalogo.IDCat = dr.GetOracleDecimal(0).Value;
                //catalogo.ano = dr.GetOracleString(4).Value;
                //catalogo.caminho = dr.GetOracleString(10).Value;
                catalogo.IDCategoria = dr.GetOracleDecimal(5).Value;
                catalogo.imagem = Convert.ToBase64String(k);
                catalogo.sinopse = dr.GetOracleString(11).Value;
                catalogo.nome = dr.GetOracleString(12).Value;
                catalogo.ano = dr.GetOracleDecimal(4).Value;

                ret = catalogo.ToString();
                listJson.Add(catalogo);
                int a = retorno.Length;
            }
            dynamic catalogos = new JsonObject();
            catalogos.catalogo = listJson;
            cn.Desconectar();
            return catalogos.ToString();
        }
    }
}