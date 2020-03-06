using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

using Oracle.DataAccess.Client;
using System.Data;

using FilmesJaWS.util;

namespace FilmesJaWS.DAO
{
    public class DAOGeral
    {
        private OracleCommand cmd;
        private ClasseConexao cn;

        public string ConsultaGeralJson(string sql)
        {
            DataTable dt = new DataTable();
            cn = new ClasseConexao();
            cmd = new OracleCommand(sql, cn.getConnection());
            cn.Conectar();
            OracleDataAdapter da = new OracleDataAdapter(cmd);
            da.Fill(dt);
            System.Web.Script.Serialization.JavaScriptSerializer seializer = new System.Web.Script.Serialization.JavaScriptSerializer();
            List<Dictionary<string, object>> rows = new List<Dictionary<string, object>>();
            Dictionary<string, object> row;

            foreach (DataRow dr in dt.Rows)
            {
                row = new Dictionary<string, object>();
                foreach (DataColumn col in dt.Columns)
                {
                    if (col.DataType == System.Type.GetType("System.Byte[]"))
                    {
                        try
                        {
                            if (!dr.IsNull(col))
                            {
                                string imageCode = Convert.ToBase64String((byte[])dr[col]);
                                row.Add(col.ColumnName, imageCode);
                            }
                            else
                            {
                                row.Add(col.ColumnName, dr[col]);
                            }
                        }
                        catch (Exception)
                        {
                            row.Add(col.ColumnName, dr[col]);
                        }
                        
                    }

                    else if (col.DataType == System.Type.GetType("System.DateTime"))
                    {
                        try
                        {
                            if (!dr.IsNull(col))
                            {
                                string data = ((DateTime)dr[col]).ToString("dd/MM/yyyy");
                                row.Add(col.ColumnName, data);
                            }
                            else
                            {
                                row.Add(col.ColumnName, dr[col]);
                            }
                           
                        }
                        catch (Exception)
                        {
                            row.Add(col.ColumnName, dr[col]);
                        }
                      
                    }

                    else
                    {
                        row.Add(col.ColumnName, dr[col]);
                    }
                    
                }

                rows.Add(row);
            }

            cn.Desconectar();
            return seializer.Serialize(rows);

        }


        public string Inserir(string sql)
        {
            try
            {
                cn = new ClasseConexao();
                cn.Conectar();
                cmd = new OracleCommand(sql, cn.getConnection());
                cmd.ExecuteNonQuery();
                cn.Desconectar();
            }
            catch (Exception ex)
            {
                return ex.Message;
            }
            return "1";
        }
    }
}