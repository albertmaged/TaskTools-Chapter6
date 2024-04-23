package App;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import EJB.Calculator;

@Path("calculate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless


public class CalculationService {
    @PersistenceContext(unitName="Calculation")
    private EntityManager entityManager;
@POST
@Path("calc")
    public double performCalculation(Calculator c)
    {
        
        if (c.getOperation()=="+") {
            return c.getNumber1() + c.getNumber2() ;
        } else if (c.getOperation()=="-") {
        	return c.getNumber1() - c.getNumber2();
        } else if (c.getOperation()=="*") {
        	return c.getNumber1() * c.getNumber2() ;
        } else if (c.getOperation()=="/") {
        	return c.getNumber1() / c.getNumber2() ;
        }
        entityManager.persist(c);
        
        	return 0 ;
    }
        @GET
        @Path("calculations")
        public List<Calculator> getAllCalculations() {
            TypedQuery<Calculator> query = entityManager.createQuery("SELECT c FROM Calculator c", Calculator.class);
            return query.getResultList();
        }
    }