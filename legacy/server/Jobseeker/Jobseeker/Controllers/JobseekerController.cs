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
    public class JobseekerController : ApiController
    {
        /*
        public IEnumerable<Seeker> Get()
        {
            new Database().Connect();
            return new Seeker().GetAll();
        }
        */
        public Seeker Get(int id)
        {
            return new Seeker().GetJobSeeker(id);
        }

        /*
        public IEnumerable<Seeker> Post(Seeker seeker)
        {
            return new Seeker().AddJobseeker(seeker);
        }
        */

            //Temp POST override
        public Seeker Post(Seeker seeker)
        {
            new Seeker().AddJobseeker(seeker);

            return seeker;
        }

        [HttpGet]
        public DataTable GetForCompany()
        {
            return new Seeker().GetForCompany();
        }

        public IEnumerable<Seeker> Put(Seeker seeker)
        {
            return new Seeker().UpdateJobSeeker(seeker);
        }
        [HttpDelete]
        public IEnumerable<Seeker> Delete(int id)
        {
            return new Seeker().Delete(id);
        }
    }
}
