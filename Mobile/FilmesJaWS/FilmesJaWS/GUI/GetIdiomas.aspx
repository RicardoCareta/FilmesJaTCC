<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="GetIdiomas.aspx.cs" Inherits="FilmesJaWS.GUI.GetIdiomas" %>
<%@ Import Namespace="FilmesJaWS.DAO" %>
<%

    DAOIdioma daoIdioma = new DAOIdioma();
    Response.Write(daoIdioma.getIdiomas());

     %>