using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Json;

using Oracle.DataAccess.Client;

namespace FilmesJaWS.DAO
{
    public class DAOIdioma
    {
        private OracleCommand cmd;
        private util.ClasseConexao cn;

        private OracleDataReader dr;

        public string getIdiomas()
        {
            string ret = "";
            dynamic listJson = new JsonArray();
            cn = new util.ClasseConexao();
            cn.Conectar();
            cmd = new OracleCommand("select * from TBLIDIOMA where ATIVO = 1", cn.getConnection());
            dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                string retorno = "";
                dynamic idioma = new JsonObject();

                idioma.id = dr.GetOracleDecimal(0).Value;
                idioma.nome = dr.GetOracleString(1).Value;


                ret = idioma.ToString();
                listJson.Add(idioma);
                int a = retorno.Length;
            }

            dynamic idiomas = new JsonObject();
            idiomas.idiomas = listJson;
            cn.Desconectar();
            return idiomas.ToString();
        }
    }
}