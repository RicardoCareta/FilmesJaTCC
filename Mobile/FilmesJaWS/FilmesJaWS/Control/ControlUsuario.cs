using FilmesJaWS.DAO;
using FilmesJaWS.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace FilmesJaWS.Control
{
    public class ControlUsuario
    {
        private List<ModelUsuario> lista;

        private DAOUsuario daoUsuario;

        public ControlUsuario()
        {
            //lista = daoUsuario.Selecionar();
        }

        public bool Login()
        {
            return true;
        }
    }
}