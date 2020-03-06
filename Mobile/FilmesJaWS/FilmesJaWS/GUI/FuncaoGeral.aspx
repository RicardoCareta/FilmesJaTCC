<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="FuncaoGeral.aspx.cs" Inherits="FilmesJaWS.GUI.FuncaoGeral" %>
<% FilmesJaWS.DAO.DAOGeral daoGeral = new FilmesJaWS.DAO.DAOGeral();
    string sql = Request.QueryString["sql"];

    if (Request.QueryString["tp"] == null)
    {
        Response.Write("Operação Invalida");
        return;
    }


    string tipo = Request.QueryString["tp"];

    if (tipo == "1")
    {
        string json = daoGeral.ConsultaGeralJson(sql);
        Response.Write("{'RESULTADO':" + json + "}");
        return;
    }

    if (tipo == "2")
    {
        Response.Write(daoGeral.Inserir(sql));
    }


    %>