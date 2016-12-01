using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using Jobseeker.Models;
using System.Data;

namespace Jobseeker.Controllers
{
    public class UsersController : ApiController
    {
        public DataTable Get()
        {
            new Database().Connect();
            return new User().GetAll();
        }

        public DataTable Get(int id)
        {
            return new User().GetUser(id);
        }


        public DataTable Post(User user)
        {
            return new User().AddUser(user);
        }

        [ActionName("SignIn")]
        public DataTable SignIn(Login login)
        {
            return new User().Login(login);
        }
        [HttpGet]
        [ActionName("CheckProfile")]
        public bool CheckProfile(int id)
        {
            return new User().isProfiled(id);
        }

        public DataTable Put(User user)
        {
            return new User().UpdateUser(user);
        }

        public DataTable Delete(int id)
        {
            return new User().Delete(id);
        }

    }
}
