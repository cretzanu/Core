package eu.cec.digit.apps.meta.services.rest;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.business.apps.core.data.document.enums.DomainType;
import com.business.apps.core.data.document.enums.DossierType;
import com.business.apps.core.data.document.enums.EnumParamsIdentifiers;
import com.business.apps.core.data.document.enums.ServiceReferenceType;


public class UserContextFilter implements Filter {

	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest) arg0;
//		String providedServiceIdParam = request
//				.getParameter("providedServiceId");
//		if (providedServiceIdParam != null) {
//			request.getSession(true).setAttribute(
//					RequisitionAPPController.PROVIDED_SERVICE_ID_PARAM,
//					providedServiceIdParam);
//			// System.out.println("--------------------FOUND provided service param!"+providedServiceIdParam);
//		} else {// OR - the other way around (multiple params to identify the
//				// provided service)
//
//			String dossierType = request.getParameter(EnumParamsIdentifiers.DOSSIER_TYPE);
//			String domainType = request.getParameter(EnumParamsIdentifiers.DOMAIN_TYPE);
//			String serviceReferenceType = request.getParameter(EnumParamsIdentifiers.SERVICE_REFERENCE_TYPE);
//			String serviceProvider = request.getParameter(EnumParamsIdentifiers.SERVICE_PROVIDER);
//			//http://127.0.0.1:7001/digit-customer-portal/requisition/rest/main/menu?dossierType=PROVISION_OF_GOODS&domainType=LIBRARY&serviceReferenceType=REQUISITION&serviceProvider=%5C%5CEU%5CCE%5CDGT
//			if (dossierType != null && domainType != null
//					&& serviceReferenceType != null && serviceProvider!=null) {
//				request.getSession(true).setAttribute(
//						RequisitionAPPController.DOMAIN_TYPE_PARAM,
//						DomainType.valueOf(domainType));
//				request.getSession(true).setAttribute(
//						RequisitionAPPController.DOSSIER_TYPE_PARAM,
//						DossierType.valueOf(dossierType));
//				request.getSession(true).setAttribute(
//						RequisitionAPPController.SERVICE_REFERENCE_TYPE_PARAM,
//						ServiceReferenceType.valueOf(serviceReferenceType));
//				request.getSession(true).setAttribute(
//						RequisitionAPPController.SERVICE_PROVIDER_PARAM,
//						serviceProvider);
//			}
//		}

		chain.doFilter(arg0, arg1);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args){
		System.out.println(URLEncoder.encode("\\\\EU\\CE\\DGT"));
	}
}
