/**
 * Auto generated file comment
 */
package org.openmrs.module.pharmacymanagement.stock.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openmrs.api.context.Context;
import org.openmrs.module.pharmacymanagement.ProductReturnStore;
import org.openmrs.module.pharmacymanagement.service.DrugOrderService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

/**
 *
 */
public class PrintReturnStock extends ParameterizableViewController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();

		List<ProductReturnStore> returnStoreList = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DrugOrderService service = Context.getService(DrugOrderService.class);
		
		if(request.getParameter("isChecked") != null && !request.getParameter("isChecked").equals("")) {
			if(request.getParameter("isChecked").equals("1")) {
				if(request.getParameter("retDate") != null && !request.getParameter("retDate").equals("")) {
					Date date = sdf.parse(request.getParameter("retDate"));
					returnStoreList = service.getReturnStockByDate(date);
					for(ProductReturnStore ars : returnStoreList) {
						System.out.println("Pharmacy: "+ars.getOriginPharmacy());
						System.out.println("Location: "+ars.getOriginLocation());
					}
					mav.addObject("returnStoreList", returnStoreList);
				}
			}
		}
		return mav;
	}
}
