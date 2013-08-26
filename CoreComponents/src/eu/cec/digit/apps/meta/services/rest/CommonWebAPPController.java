package eu.cec.digit.apps.meta.services.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import com.business.apps.core.data.document.enums.CountryCode;
import com.business.apps.core.data.document.enums.DestinationType;
import com.business.apps.core.data.document.enums.Status;
import com.business.apps.core.data.search.criteria.Operator;

import eu.cec.digit.apps.bip.services.router.BIPMessage;
import eu.cec.digit.apps.bip.services.router.BIPRouter;
import eu.cec.digit.apps.meta.controller.crud.CRUDController;
import eu.cec.digit.apps.meta.messages.AddSystemInfoToEntities;
import eu.cec.digit.apps.meta.messages.AppMessage;
import eu.cec.digit.apps.meta.processes.Router;
import eu.cec.digit.apps.meta.processes.rules.MessageValidationException;

public abstract class CommonWebAPPController extends CRUDController
{
	/**
	 * the main router
	 */
	private static final String APP_ROUTER_1 = "APP_ROUTER_1";
public static final String PROVIDED_SERVICE_ID_PARAM = "providedServiceId";
  private static final String SESSION_ATT_MAIN_USER_CONTEXT = "MAIN_USER_CONTEXT";
  private static final String SESSION_ATT_MAIN_USER_NAME = "MAIN_USER_NAME";
  private static final String SESSION_ATT_REQUISITION_ROUTER = "1_ROUTER";
public static final String DOMAIN_TYPE_PARAM = "DOMAIN_TYPE_PARAM";
public static final String DOSSIER_TYPE_PARAM = "DOSSIER_TYPE_PARAM";
public static final String SERVICE_REFERENCE_TYPE_PARAM = "SERVICE_REFERENCE_TYPE_PARAM";
public static final String SERVICE_PROVIDER_PARAM = "SERVICE_PROVIDER_PARAM";
  private static Properties configs = new Properties();

  @javax.ws.rs.core.Context
  HttpServletRequest request;
  
  private static BIPRouter bipRouter;
 
 
  protected <R> R sendMessage(AppMessage<R> message)
  {
    try
    {
      addSystemInfo(message);

      return (R) super.handleMessage(message);
    }
    catch (MessageValidationException t) {
      throw new ValidationException(t.getErrors());
    }
    catch (RuntimeException t) {
      if (t.getMessage().contains("javax.persistence.OptimisticLockException")) {
        Map errors = new HashMap();
        errors.put("optimisticLockEx_ERROR", "info.exception.optimisticLockException");
        throw new OptimisticLockException(errors);
      }
      throw new InternalServerException(t);
    }
  }

  protected <E> List<E> handleMessageForLists(AppMessage message, Class<E> classType)
  {
    try {
      addSystemInfo(message);
      return (List)super.handleMessage(message);
    } catch (RuntimeException t) {
      throw new InternalServerException(t);
    }
  }

  private void addSystemInfo(AppMessage message)
  {
    if ((message instanceof AddSystemInfoToEntities))
      ((AddSystemInfoToEntities)message).updateEntities(getUserName(), new Date());
  }
  /**
   * the JNDI name of this app's EJB router. 
   * @return
   */
  public abstract  String getRouterEJBJNDIName();
  public  Router getRouter()
  {
    if (this.router == null)
      if (this.request.getSession(true).getAttribute(APP_ROUTER_1) != null) {
        this.router = ((Router)this.request.getSession(true).getAttribute(APP_ROUTER_1));
      }
      else
      {
        try
        {
          this.router = ((Router)new InitialContext().lookup(this.getRouterEJBJNDIName()));
          this.request.getSession().setAttribute(APP_ROUTER_1, this.router);
        }
        catch (NamingException e) {
          e.printStackTrace();
          throw new RuntimeException("cannot find the router!");
        }
      }
    return this.router;
  }

 
//  public String getUserName()
//  {
//    
//    return getUserContext().getUserName();
//  }
//
//  protected void setDocumentContext(Document req) {
//		ServiceUserContext userContext = this.getUserContext();
//		req.setContactPersonDetail(userContext.getOrganizationDetailDefault().getContactPersonDetails());
//		req.getContext().setDomainType(userContext.getService().getDomainType());
//		req.getContext().setDossierType(userContext.getService().getDossierType());
//		req.getContext().setServiceReference(userContext.getService().getServiceReference());
//		req.getContext().setServiceProvider(userContext.getServiceProvider().getStructOrgKeyCd());
//	}
//  
//	public ServiceUserContext getUserContext() {
//		try {
//			if (this.request.getSession(true).getAttribute(
//					SESSION_ATT_MAIN_USER_CONTEXT) == null) {
//				String userName = null;
//				try {
//					System.out.println(this.request.getUserPrincipal()
//							.getName());
//					userName = this.request.getUserPrincipal().getName();
//				} catch (Throwable t) {
//					System.out
//							.println("GET USER ERROR (ignore if testing secu-disabled app)"
//									+ t);
//				}
//				String providedServiceIdParam = (String) this.request
//						.getSession().getAttribute(PROVIDED_SERVICE_ID_PARAM);
//				if (providedServiceIdParam == null) {// try the other connect
//														// option by service
//														// identifiers
//					if (this.request.getSession().getAttribute(
//							DOMAIN_TYPE_PARAM) == null)// neither this one, then
//														// no way to identify
//														// the ProvidedService
//						throw new ProvidedServiceUnknownException();
//				}
//
//				ServiceUserContext info = null;
//				if (providedServiceIdParam != null) { //the easy way
//					Long providedServiceId = null;
//					try {
//						providedServiceId = Long
//								.valueOf(providedServiceIdParam);
//					} catch (Throwable t) {
//						t.printStackTrace();
//						throw new ProvidedServiceUnknownException();
//					}
//
//					info = (ServiceUserContext) getBipRouter().handleMessage(
//							new CheckInServiceMsg(providedServiceId, userName));
//
//				} else { //the hard way
//					info = (ServiceUserContext) getBipRouter()
//							.handleMessage(
//									new CheckInServiceByParamsMsg(
//											userName,
//											(DomainType) this.request
//													.getSession().getAttribute(
//															DOMAIN_TYPE_PARAM),
//											(DossierType) this.request
//													.getSession().getAttribute(
//															DOSSIER_TYPE_PARAM),
//											(ServiceReferenceType) this.request
//													.getSession()
//													.getAttribute(
//															SERVICE_REFERENCE_TYPE_PARAM),
//											(String) this.request
//													.getSession()
//													.getAttribute(
//															SERVICE_PROVIDER_PARAM)));
//
//				}
//
//				System.out.println("updating ContactPersonDetails for user:"
//						+ userName);
//
//				ContactPersonDetail local = getAppRouter().handleMessage(
//						new UpdateContactPersonDetailExt(info
//								.getOrganizationDetailDefault()
//								.getContactPersonDetails()));
//
//				info.getOrganizationDetailDefault().setContactPersonDetails(
//						local);
//				this.request.getSession(true).setAttribute(
//						SESSION_ATT_MAIN_USER_CONTEXT, info);
//			}
//
//			return (ServiceUserContext) this.request.getSession(true)
//					.getAttribute("MAIN_USER_CONTEXT");
//		} catch (Throwable e) {
//			e.printStackTrace();
//			System.out.println("Returning default test user");
//		}
//		return null;
//	}

 

 

  public <R> R sendBIPMessage(BIPMessage<R> request)
  {
    try {
      return getBipRouter().handleMessage(request);
    } catch (Throwable e) {
      throw new InternalServerException(e.getCause());
    }
  }

  public String reSetBipRouter() {
    bipRouter = null;
    return getBipRouter().toString();
  }

  private BIPRouter getBipRouter()
  {
    if (bipRouter == null)
    {
      try
      {
        bipRouter = (BIPRouter)new InitialContext().lookup("BIPRouterEJB#eu.cec.digit.apps.bip.services.router.BIPRouter");

        System.out.println("lookup BIPRouter in requisition");
      } catch (NamingException e) {
        e.printStackTrace();
        throw new RuntimeException("cannot obtain the BIP router!");
      }
    }

    return bipRouter;
  }

  public void setBipRouter(BIPRouter bipRouter) {
    CommonWebAPPController.bipRouter = bipRouter;
  }

 

  /**
   * simple built in validators
   * @param op
   * @return
   */
  
  private Boolean isValidDateOperator(Operator op)
  {
    Boolean isValid = Boolean.valueOf(false);
    if ((op == Operator.EQUALS_TO) || (op == Operator.GREATER_THAN_OR_EQUALS_TO) || (op == Operator.LESS_THEN_OR_EQUALS_TO) || (op == Operator.BETWEEN)) {
      return Boolean.valueOf(true);
    }

    return isValid;
  }

  private Boolean isValidTextOperator(Operator op)
  {
    Boolean isValid = Boolean.valueOf(false);
    if ((op == Operator.EQUALS_TO) || (op == Operator.IS) || (op == Operator.IS_NOT) || (op == Operator.LIKE)) {
      return Boolean.valueOf(true);
    }

    return isValid;
  }

  private Boolean isValidNumericOperator(Operator op)
  {
    Boolean isValid = Boolean.valueOf(false);
    if ((op == Operator.EQUALS_TO) || (op == Operator.GREATER_THAN_OR_EQUALS_TO) || (op == Operator.LESS_THEN_OR_EQUALS_TO)) {
      return Boolean.valueOf(true);
    }

    return isValid;
  }

  private Boolean isValidOperatorForDropDown(Operator op)
  {
    Boolean isValid = Boolean.valueOf(false);
    if ((op.compareTo(Operator.IS) == 0) || (op.compareTo(Operator.IS_NOT) == 0)) {
      return Boolean.valueOf(true);
    }

    return isValid;
  }

  
  
  
  public Boolean isValidStatus(Status status)
  {
    for (Status s : Status.values()) {
      if (s.name().equalsIgnoreCase(status.name()))
      {
        return Boolean.valueOf(true);
      }
    }
    return Boolean.valueOf(false);
  }

  public Boolean isValidCountry(String countryCode) {
    for (CountryCode c : CountryCode.values()) {
      if (c.name().equalsIgnoreCase(countryCode))
      {
        return Boolean.valueOf(true);
      }
    }
    return Boolean.valueOf(false);
  }

  public Boolean isValidDestination(String destination) {
    for (DestinationType d : DestinationType.values()) {
      if (d.name().equalsIgnoreCase(destination))
      {
        return Boolean.valueOf(true);
      }
    }
    return Boolean.valueOf(false);
  }
}
