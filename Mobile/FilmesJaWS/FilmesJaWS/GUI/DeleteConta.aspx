<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="DeleteConta.aspx.cs" Inherits="FilmesJaWS.GUI.DeleteConta" %>
<%@ Import Namespace="FilmesJaWS.DAO" %>

<%
    string pIDConta = "IDCONTA";

    DAOConta c = new DAOConta();
    int idConta = 0; 
    if (Request.Form[pIDConta] != null)
    {
        idConta = int.Parse(Request.Form[pIDConta].ToString());
    }
    Response.Write(c.DeletarConta(idConta));
    %>
