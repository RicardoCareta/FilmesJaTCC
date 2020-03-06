using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

using System.Json;
using Oracle.DataAccess.Client;

namespace FilmesJaWS.DAO
{
    public class DAOFavorito
    {
        private OracleCommand cmd;
        private OracleDataReader dr;
        private util.ClasseConexao cn;

        public DAOFavorito()
        {
            cn = new util.ClasseConexao();
        }

        public string getFavoritosConta(int IDConta)
        {
            string ret = "";
            dynamic listJson = new JsonArray();

            cn.Conectar();
            cmd = new OracleCommand("select f.*, c.Imagem, c.Ano, c.Sinopse from Favoritos f inner join TBLCatalogo c on f.IDCat = c.IDCat and AtivoF = 1 and AtivoC = 1 and AtivoCO = 1 and f.IDSerieCatalogo = 0 and IDConta = " + IDConta, cn.getConnection());
            dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                string retorno = "";
                dynamic favorito = new JsonObject();
                byte[] k = null;
                if (dr.GetOracleBlob(8) != null)
                {
                    k = dr.GetOracleBlob(8).Value;
                }

                favorito.nome = dr.GetOracleString(3).Value;
                favorito.imagem = Convert.ToBase64String(k);
                favorito.idCat = dr.GetOracleDecimal(2).Value;
                favorito.ano = dr.GetOracleDecimal(9).Value;
                favorito.sinopse = dr.GetOracleString(10).Value;

                ret = favorito.ToString();
                listJson.Add(favorito);
                int a = retorno.Length;
            }

            dynamic favoritos = new JsonObject();
            favoritos.favoritos = listJson;
            cn.Desconectar();
            return favoritos.ToString();
        }
    }
}