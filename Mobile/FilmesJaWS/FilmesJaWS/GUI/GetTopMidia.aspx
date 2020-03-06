<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="GetTopMidia.aspx.cs" Inherits="FilmesJaWS.GUI.GetTopMidia" %>

<%@ Import Namespace="FilmesJaWS.DAO" %>
<%
    DAOCatalogo catalogo = new DAOCatalogo();
    Response.Write(catalogo.getTopFilmes() + "\nteste");
    %>
