<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="PegarContas.aspx.cs" Inherits="FilmesJaWS.GUI.PegarContas" %>
<%@ Import Namespace="FilmesJaWS.DAO" %>
<%
    string pIDUsuario = "IDUSUARIO";

    DAOUsuario usuario = new DAOUsuario();
    string IDUsuario = "1";

    if (Request.Form[pIDUsuario] != null)
    {
        IDUsuario = Request.Form[pIDUsuario].ToString();
    }

    Response.Write(usuario.getPerfilsConta(int.Parse(IDUsuario)));

     %>