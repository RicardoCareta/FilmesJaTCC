using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Json;

using FilmesJaWS.Model;

using Oracle.DataAccess.Client;
using System.Diagnostics;

namespace FilmesJaWS.DAO
{
    public class DAOUsuario
    {
        private OracleCommand cmd;
        private OracleDataReader dr;
        private util.ClasseConexao cn;

        public int IDUsuario;

        public DAOUsuario()
        {
            cn = new util.ClasseConexao();
        }

        public bool Login(string Email, string Senha)
        {
            cn.Conectar();
            //Adicionar parametros depois, para não ter chance de SQL Injection
            //cmd = new OracleCommand("select LoginUsuario('"+Email+"', '"+Senha+"') from dual", cn.getConnection());
            cmd = new OracleCommand("select IDCLIENTE from tblUsuarioCliente su where su.EMAIL = '" + Email + "' and su.SENHA = '" + Senha + "'", cn.getConnection());

            dr = cmd.ExecuteReader();
            bool retorno = false;

            while (dr.Read())
            {
                IDUsuario = int.Parse(dr.GetOracleDecimal(0).Value.ToString());
                retorno = true;
            }

            dr.Close();
            cn.Desconectar();

            return retorno;
        }
        public string getPerfilsConta(int IDCliente)
        {
            string ret = "";

            dynamic listJson = new JsonArray();

            cn.Conectar();
            cmd = new OracleCommand("select * from tblConta c inner join tblUsuarioCliente u on c.IDcliente = u.IDCliente and c.Ativo = 1 and c.IDcliente =" + IDCliente, cn.getConnection());
            dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                string retorno = "";
                dynamic conta = new JsonObject();
                byte[] k = null;
                if (dr.GetOracleBlob(3) != null)
                {
                    k = dr.GetOracleBlob(3).Value;
                }

                conta.nome = dr.GetOracleString(1).Value;

                //sretorno += "{\r\n'nome': '" + dr.GetOracleString(1).Value + "',\r\n'imageb':";

                conta.imagem = Convert.ToBase64String(k);
                conta.IDConta = dr.GetOracleDecimal(0).Value;
                conta.data = dr.GetOracleDate(4).Value.ToString();
                //retorno += "',\r\n'IDConta': '" + dr.GetOracleDecimal(0).Value + "'} \r\n";
                ret = conta.ToString();
                listJson.Add(conta);
                int a = retorno.Length;
}

            dynamic contas = new JsonObject();
            contas.contas = listJson;
            cn.Desconectar();
            return contas.ToString();
        }
    }
}