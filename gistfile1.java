package routines;

import generated.Addr;
import generated.Application;
import generated.BasicPolicy;
import generated.Customer;
import generated.DTOARTrans;
import generated.DTOAccount;
import generated.DTOApplication;
import generated.DTOAssignedProvider;
import generated.DTOBasicPolicy;
import generated.DTOClaim;
import generated.DTOClaimPolicyInfo;
import generated.DTOClaimTransactionHistory;
import generated.DTOClaimTransactionInfo;
import generated.DTOClaimant;
import generated.DTOClaimantTransaction;
import generated.DTOCoverage;
import generated.DTOCustomer;
import generated.DTODeductible;
import generated.DTOFeature;
import generated.DTOFeatureAllocation;
import generated.DTOGLClass;
import generated.DTOInsured;
import generated.DTOLimit;
import generated.DTOLine;
import generated.DTOLitigation;
import generated.DTOLossHistory;
import generated.DTOOtherInsurance;
import generated.DTOPropertyDamaged;
import generated.DTOReserve;
import generated.DTOReserveAllocation;
import generated.DTORisk;
import generated.DTOTransactionHistory;
import generated.DTOTransactionInfo;
import generated.DTOTransactionReason;
import generated.DTOTransactionText;
import generated.ElectronicPaymentDestination;
import generated.ElectronicPaymentSource;
import generated.EmailInfo;
import generated.LinkReference;
import generated.NameInfo;
import generated.Note;
import generated.PartyInfo;
import generated.PersonInfo;
import generated.PhoneInfo;
import generated.Policy;
import generated.QuestionReplies;
import generated.QuestionReply;
import generated.Questions;
import generated.Tag;
import generated.TaxInfo;
import generated.TransactionHistory;
import generated.TransactionText;
import generated.UWExternalPolicyListLoadRs;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.gopetplan.FetchQuoteResponse;
import com.gopetplan.FimsServiceSoap;
import com.gopetplan.IssuePolicy;
import com.gopetplan.IssuePolicyResponse;
import com.gopetplan.PolicyType;

public class Utils {

	public static final String CHECK = "Check";
	public static final String MANUAL_REFUND = "ManualRefund";
	public static final String CASH = "Cash";
	public static final String WAIVE_CREDIT = "WaiveCredit";
	public static final String WAIVE_DEBIT = "WaiveDebit";
	public static final String POLICY_FORM_PLATINUM = "Platinum";
	public static final String TRANSACTION_CD_ENDORSEMENT = "Endorsement";
	public static final String TRANSACTION_CD_CANCELLATION_NOTICE = "Cancellation Notice";
	public static final String TRANSACTION_CD_PLACE_ON_HOLD = "Place on Hold";
	public static final String TRANSACTION_CD_EXPIRE = "Expire";
	public static final String STATUS_CD_CANCELLATION_NOTICE = "Cancel Notice";
	public static final String PARTY_TYPE_CD_INSURED_PARTY = "InsuredParty";
	public static final String PARTY_TYPE_CD_CUSTOMER_PARTY = "CustomerParty";
	public static final String PETPLAN_SOURCE_CD = "Petplan";
	public static final String TRANSACTION_TYPE_CD = "Transaction";
	public static final String CLAIM_TYPE_CD = "Claim";
	public static final String STATUS_DELETED = "Deleted";
	public static final String COVERAGE_CD_VET = "VET";
	public static final String PHONE_NAME_MOBILE = "Mobile";
	public static final String PHONE_TYPE_CD_INSURED_PHONE_PRIMARY = "InsuredPhonePrimary";
	public static final String LAPSED_RECORD_STATUS_POLICY = "Lapsed";
	public static final String TRANS_REASON_CD_INSURED_REQUEST = "InsuredRequest";
	public static final String POLICY_RECORD_STATUS_POLICY = "Policy";
	public static final String POLICY_RECORD_STATUS_CANCELLED = "Cancelled";
	public static final String PAYMENT_TYPE_CD_NONE = "None";
	public static final String USER_CONVERSION = "Conversion";
	public static final String TRANS_CODE_CANCELLATION = "Cancellation";
	public static final String TRANS_CODE_RENEWAL = "Renewal";
	public static final String TRANS_CODE_REWRITE_RENEWAL = "Rewrite-Renewal";
	public static final String CREDIT_CARD_ELECTRONIC_PAYMENT_METHOD = "Credit Card";
	public static final String INSTALLMENT_SOURCE_ELECTRONIC_PAYMENT_SOURCE_TYPE = "InstallmentSource";
	public static final String TRANSACTION_SOURCE_CODE = "Petplan";
	public static final String G1_PAY_PLAN_TYPE = " G1 ";
	public static final String G2_PAY_PLAN_TYPE = " G2 ";
	public static final String TAX_ID_TYPE_CODE_SSN = "SSN";
	public static final String TAX_TYPE_CODE_INSURED_TAX_INFO = "InsuredTaxInfo";
	public static final String ADDR_TYPE_CODE_INSURED_LOOKUP_ADDR = "InsuredLookupAddr";
	public static final String TAX_TYPE_CODE_CUSTOMER_TAX_INFO = "CustomerTaxInfo";
	public static final String ADDR_TYPE_CODE_CUSTOMER_LOOKUP_ADDR = "CustomerLookupAddr";
	public static final String POLICY_SUBTYPE_PLP = "PLP";
	public static final String PHONE_TYPE_CODE_INSURED_PHONE_PRIMARY = PHONE_TYPE_CD_INSURED_PHONE_PRIMARY;
	public static final String PHONE_TYPE_CODE_INSURED_PHONE_SECONDARY = "InsuredPhoneSecondary";
    public static final String PHONE_TYPE_CODE_INSURED_FAX = "InsuredFax";
    public static final String PHONE_TYPE_CODE_CUSTOMER_PHONE_PRIMARY = "CustomerPhonePrimary";
	public static final String PHONE_TYPE_CODE_CUSTOMER_PHONE_SECONDARY = "CustomerPhoneSecondary";
    public static final String PHONE_TYPE_CODE_CUSTOMER_FAX = "CustomerFax";
	public static final String ADD_TYPE_CODE_EXPOSURE_ADDR = "ExposureAddr";
	public static final String RATING_SERVICE_MANUAL_RATE = "ManualRate";
	public static final String LINE_CODE_PET = "Pet";
	public static final String ACTIVE = "Active";
	public static final String ADDR_TYPE_CODE_INSURED_BILLING_ADDR = "InsuredBillingAddr";
	public static final String ADDR_TYPE_CODE_INSURED_MAILING_ADDR = "InsuredMailingAddr";
	public static final String ADDR_TYPE_CODE_CUSTOMER_BILLING_ADDR = "CustomerBillingAddr";
	public static final String ADDR_TYPE_CODE_CUSTOMER_MAILING_ADDR = "CustomerMailingAddr";
	public static final String NAME_TYPE_CODE_INSURED_NAME = "InsuredName";
	public static final String NAME_TYPE_CODE_CUSTOMER_NAME = "CustomerName";
	public static final String INDICATOR_YES = "Yes";
	public static final String INDICATOR_NO = "No";
	public static final String EMAIL_TYPE_CODE_INSURED_EMAIL = "InsuredEmail";
	public static final String EMAIL_TYPE_CODE_INSURED_EMAIL_SECONDARY = "InsuredEmailSecondary";
	public static final String EMAIL_TYPE_CODE_CUSTOMER_EMAIL = "CustomerEmail";
	public static final String TRANSACTION_TYPE_CODE_ITEM_ADJUSTMENT = "ItemAdjustment";
	public static final String SOURCE_CODE_CLAIM = CLAIM_TYPE_CD;
	public static final String TRANSACTION_TYPE_CODE_RECEIPT = "Receipt";
	public static final String PROVIDER_NUMBER = "PETPLAN-P";
	public static final String TRANS_CODE_NEW_BUSINESS = "New Business";
	public static final String CARRIER_CODE = "ALZ";
	public static final String CONTROLLING_STATE = "PA";
	public static final String APP_DESCRIPTION_NEW_QUOTE = "New Policy";
	public static final String APP_STATUS_NEW_SUBMISSION = "NewSubmission";
	public static final String APP_STATUS_IN_PROCESS = "In Process";
	public static final String TYPE_CD_APPLICATION = "Application";
	public static final String TYPE_CD_QUOTE = "Quote";
	
	private static Random RANDOM = new Random();
	public static DateFormat DATE_FRMT = new SimpleDateFormat("yyyyMMdd");
	public static DateFormat TIME_FRMT = new SimpleDateFormat("HH:mm:ss z"); // 14:22:04 EDT
	
	public static JAXBContext JAXB_DTO_APPLICATION = null;
	public static JAXBContext JAXB_APPLICATION = null;
	public static JAXBContext JAXB_DTO_CUSTOMER = null;
	public static JAXBContext JAXB_CUSTOMER = null;
	public static JAXBContext JAXB_DTO_ACCOUNT = null;
	public static JAXBContext JAXB_QUESTIONS = null;
	public static JAXBContext JAXB_POLICY = null;
	public static JAXBContext JAXB_EXTERNAL_POLICY_LIST_LOAD_RS = null;
	public static JAXBContext JAXB_DTO_COVERAGE = null;
	public static JAXBContext JAXB_DTO_CLAIM = null;
	public static JAXBContext JAXB_PROVIDER = null;
	static{
		try{
			JAXB_APPLICATION = JAXBContext.newInstance(Application.class);
			JAXB_DTO_APPLICATION = JAXBContext.newInstance(DTOApplication.class);
			JAXB_DTO_CUSTOMER = JAXBContext.newInstance(DTOCustomer.class);
			JAXB_CUSTOMER = JAXBContext.newInstance(Customer.class);
			JAXB_DTO_ACCOUNT = JAXBContext.newInstance(DTOAccount.class);
			JAXB_QUESTIONS = JAXBContext.newInstance(Questions.class);
			JAXB_POLICY = JAXBContext.newInstance(Policy.class);
			JAXB_PROVIDER = JAXBContext.newInstance(generated.Provider.class);
			JAXB_EXTERNAL_POLICY_LIST_LOAD_RS = JAXBContext.newInstance(UWExternalPolicyListLoadRs.class);
			JAXB_DTO_COVERAGE = JAXBContext.newInstance(DTOCoverage.class);
			JAXB_DTO_CLAIM = JAXBContext.newInstance(DTOClaim.class);
		}
		catch(Throwable e){}
	}
	
	/**
     * @param original
     * @return
     */
    /*public static BigBang.policyStruct deepCopy(routines.system.IPersistableRow<BigBang.policyStruct> original) {
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	
    	ObjectOutputStream oos;
    	ByteArrayInputStream bais;
		try {
			oos = new ObjectOutputStream(bos);
	    	//oos.writeObject(original);
	    	original.writeData(oos);
	    	
	    	oos.flush();
	    	oos.close();
	    	bos.close();
	    	
	    	byte[] byteData = bos.toByteArray();
	    	bais = new ByteArrayInputStream(byteData);
	    	
	    	BigBang.policyStruct o = new BigBang.policyStruct();
	    	o.readData(new ObjectInputStream(bais));
	    	return o;
	    	//return new ObjectInputStream(bais).readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }*/
    
    /**
     * @param policyDataByPolicyNumber
     * @param rewrites
     * @param rewritesOld
     * @param policyData
     * @param productVersionMappings
     * @param appsByPolicyNumber
     * @param activeOnly
     * @param previousApp
     * @return
     */
    public static DTOApplication mapPolicyData(Map<String, routines.LegacyPolicyData> policyDataByPolicyNumber, List<routines.LegacyRewrite> rewrites, List<routines.LegacyRewrite> rewritesOld, LegacyPolicyData policyData, Map<String, List<routines.ProductVersion>> productVersionMappings, Map<String, DTOApplication> appsByPolicyNumber, boolean activeOnly, DTOApplication previousApp){
    	DTOApplication app = new DTOApplication();
    	app.setId(policyData.POLICY_DETAILS_ID);
    	app.setTypeCd(TYPE_CD_APPLICATION);
    	app.setStatus(APP_STATUS_IN_PROCESS);
    	app.setDescription(APP_DESCRIPTION_NEW_QUOTE);
    	
    	//app.setReferringCustomerRef(null);
    	
    	DTOBasicPolicy basicPolicy = new DTOBasicPolicy();
    	basicPolicy.setTransactionStatus(policyData.record_status);
    	
    	app.setDTOBasicPolicy(basicPolicy);
    	
    	/*
    	<DTOApplication TypeCd="Application"
    	       Status="NewSubmission"
    	       Description="New Quote" >

    	      <DTOBasicPolicy id="HO078002-BasicPolicy" CarrierGroupCd="TEST" CarrierCd="TEST" ControllingStateCd="IL" EffectiveTm="12:01am" ProviderNumber="A0258" StatusCd="Active" PolicyForm="DW3" AutoInsuredInd="No" RenewalTermCd="1 Year" BusinessSourceCd="Other" ConversionSourceCd="TEST" Description="Dwelling" SubTypeCd="Special" OriginalEffectiveDt="19920410" PolicyNumber="HO078002"
    	             PolicyDisplayNumber="HO078002-18"
    	             PolicyVersion="18"
    	             ProductVersionIdRef="IL-19900101-DW"
    	             InceptionDt="20090410"
    	             EffectiveDt="20090410"
    	             ExpirationDt="20100410"
    	             TransactionNumber="1"
    	             PayPlanCd="direct bill full pay"
    	             WrittenPremiumAmt="406.00"
    	             FullTermAmt="406.00"
    	             FinalPremiumAmt="406.00"
    	             Commission="15.00"
    	             ConversionCommissionAmt="60.90"
    	             TransactionCd="New Business" >
    	      </DTOBasicPolicy>
    	      */
    	if(StringUtils.isBlank(policyData.getPOLICYNUMBER()) || !policyData.getPOLICYNUMBER().contains("-")){
    		return null;
    	}
    	
    	basicPolicy.setDescription("Pet Insurance");
    	basicPolicy.setPolicyNumber(getPolicyNumber(policyData.getPOLICYNUMBER()));
    	basicPolicy.setPolicyDisplayNumber(policyData.getPOLICYNUMBER());
    	basicPolicy.setProviderNumber(PROVIDER_NUMBER); // ???
    	basicPolicy.setId("BasicPolicy-" + policyData.getTRANSACTION_GROUP().setScale(0, BigDecimal.ROUND_HALF_UP)); //basicPolicy.getPolicyNumber());
    	//basicPolicy.setTransactionNumber(new BigInteger("1"));
    	
    	// Set manual renewal data if it's present.
    	if(StringUtils.isNotBlank(policyData.getManualRenewalInd())){
    		basicPolicy.setManualRenewalInd(policyData.getManualRenewalInd());
    	}
    	if(StringUtils.isNotBlank(policyData.getManualRenewalReason())){
    		basicPolicy.setManualRenewalReason(policyData.getManualRenewalReason());
    	}
    	
    	String carrierCode = policyData.getPOLICYNUMBER().substring(0,3);
    	basicPolicy.setCarrierGroupCd(carrierCode); // CARRIER_CODE
    	basicPolicy.setCarrierCd(carrierCode); // CARRIER_CODE
    	
    	// Changed on 03052015
    	//setPolicyVersion(app, policyData, null);
    	
    	basicPolicy.setSubTypeCd(POLICY_SUBTYPE_PLP);
    	basicPolicy.setProgramCd(getProgramCode(policyData));
    	basicPolicy.setPolicyForm(getPolicyForm(policyData));
    	if(policyData.CAMPAIGN_ID != null && !policyData.CAMPAIGN_ID.trim().equals("") && !policyData.CAMPAIGN_ID.trim().equals("0")){
    		basicPolicy.setCampaignCd(policyData.CAMPAIGN_ID.trim());
    	}
    	basicPolicy.setRenewalTermCd(getRenewalTermCode(policyData));
    	
    	// Set default renewal cap factor to 1.
		basicPolicy.setRenewalCapFactor(1.0000);
		
    	// Obtain renewal discount if there is one.
    	BigDecimal capDiffAmount = null;
    	if(policyData.getCAP_DIFF() != null){
    		//System.out.println("XXXCAP_DIF=" + policyData.getCAP_DIFF().toString() + " and premium=" + policyData.getPREMIUM().toString());
    		capDiffAmount = new BigDecimal(policyData.getCAP_DIFF().toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
    	}
    	
    	// 12052014: Import change
    	//basicPolicy.setFullTermAmt(new BigDecimal(policyData.getPREMIUM().toString()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    	
    	/*
    	// Code below fails since numbers come in non-currency format (235.7200)
    	try {
			basicPolicy.setFullTermAmt(NumberFormat.getCurrencyInstance().parse(policyData.getPREMIUM().toString()).doubleValue());
		} catch (ParseException e) {
			System.out.println("error for amount:" + policyData.getPREMIUM().toString());
			e.printStackTrace();
			basicPolicy.setFullTermAmt(new Double(0.00));
		}*/
    	
    	// Apply renewal discount, if there is one.
    	/*if(capDiffAmount != null){
    		basicPolicy.setFullTermAmt((basicPolicy.getFullTermAmt() - capDiffAmount.doubleValue()));
    	}*/
    	
    	// 12052014: Import change
    	//basicPolicy.setWrittenPremiumAmt(policyData.getWRITTEN_PREMIUM().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    	//basicPolicy.setFinalPremiumAmt(basicPolicy.getFullTermAmt());
    	
    	basicPolicy.setOverrideProductVersionIdRefInd(INDICATOR_YES);
    	
    	// Set payment day.
    	if(policyData.getPaymentday() != null){
    		basicPolicy.setPaymentDay(policyData.getPaymentday().toString());
    	}
    	
    	synchronized (DATE_FRMT) {
    		if(policyData.getPOLICYSTARTDATE() != null){
    			basicPolicy.setEffectiveDt(DATE_FRMT.format(policyData.getPOLICYSTARTDATE()));
			}
		}
    	synchronized (DATE_FRMT) {
    		if(policyData.getPOLICYENDDATE() != null){
    			basicPolicy.setExpirationDt(DATE_FRMT.format(policyData.getPOLICYENDDATE()));
			}
		}
    	synchronized (DATE_FRMT) {
    		if(policyData.getORIGINALINCEPTIONDATE() != null){
    			basicPolicy.setInceptionDt(DATE_FRMT.format(policyData.getORIGINALINCEPTIONDATE()));
			}
    		else{
    			basicPolicy.setInceptionDt(basicPolicy.getEffectiveDt());
    		}
		}
    	
    	// 12052014: Import change
    	//basicPolicy.setCommission(policyData.getPETPLAN_COMMISSION() != null ? policyData.getPETPLAN_COMMISSION().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() : 0D);
    	//basicPolicy.setTransactionCommissionAmt(0D);
    	//basicPolicy.setWrittenCommissionFeeAmt(0D);
    	//basicPolicy.setWrittenFeeAmt(0D);
    	basicPolicy.setConversionWrittenPremiumAmt(policyData.getCONVERSION_WRITTEN_PREMIUM_AMT() != null ? policyData.getCONVERSION_WRITTEN_PREMIUM_AMT().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() : 0D);
    	basicPolicy.setConversionCarryForwardAmt(policyData.getCONVERSION_CARRY_FORWARD_AMT() != null ? policyData.getCONVERSION_CARRY_FORWARD_AMT().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() : 0D);
    	
    	// Determine if this is a renewal policy.
    	//!policyData.POLICYNUMBER.endsWith("-00") && 
    	routines.LegacyPolicyData previousPolicyData = null;
    	if(previousApp != null){
    		previousPolicyData = policyDataByPolicyNumber.get(previousApp.getDTOBasicPolicy().getPolicyDisplayNumber());
    	}
    	boolean renewalPolicy = isRenewal(activeOnly, policyData, previousPolicyData, previousApp);
    	
    	// Controlling state must match policy state.
    	basicPolicy.setControllingStateCd(policyData.getCOUNTY());
    	List<routines.ProductVersion> prodVers = productVersionMappings.get(basicPolicy.getCarrierCd() + "-" + basicPolicy.getControllingStateCd());
    	if(prodVers == null || prodVers.isEmpty()){
    		prodVers = productVersionMappings.get(basicPolicy.getCarrierCd() + "-" + basicPolicy.getControllingStateCd()); // "-CW" - wild card for Country Wide
    	}
    	boolean matched = false;
    	if(prodVers != null){
	    	for (ProductVersion productVersion : prodVers) {
	    		
	    		/*System.out.println("Date PV:" + productVersion.startDate + "-" + productVersion.endDate 
	    				+ "\nDate BP:" + policyData.getPOLICYSTARTDATE() + "-" + policyData.getPOLICYENDDATE()
	    				+ "\nProduct Version:" + productVersion.version
	    				+ "\nPetPlan Version:" + policyData.ProductVersionIDRef
	    				+ "\nPetPlan Version ID:" + productVersion.versionId
		    			+ "\nPetPlan Policy:" + policyData.POLICYNUMBER);
	    		*/
	    		
	    		//if(policyData.getPOLICYSTARTDATE().after(productVersion.startDate) && policyData.getPOLICYSTARTDATE().before(productVersion.endDate)){
	    		
	    		if(policyData.ProductVersionIDRef.equalsIgnoreCase(productVersion.version)){
	    			/*System.out.println("Date PV:" + productVersion.startDate + "-" + productVersion.endDate 
		    				+ "\nDate BP:" + policyData.getPOLICYSTARTDATE() + "-" + policyData.getPOLICYENDDATE()
		    				+ "\nProduct Version:" + productVersion.version
		    				+ "\nPetPlan Version:" + policyData.ProductVersionIDRef
		    				+ "\nPetPlan Version ID:" + productVersion.versionId
		    				+ "\nPetPlan Policy:" + policyData.POLICYNUMBER);
	    			*/
	    			
	    			basicPolicy.setProductVersionIdRef(productVersion.versionId);
	    			basicPolicy.setPayPlanCd(getPayPlanCode(policyData, basicPolicy, productVersion, renewalPolicy, false));
	    			matched = true;
	    			break;
	    		}
			}
    	}
    	if(!matched){
    		System.err.println("Failed to get product version for:[" + basicPolicy.getCarrierCd() + "-" + basicPolicy.getControllingStateCd() + "] and ProductVersionIDRef:" + policyData.ProductVersionIDRef);
    	}
    	
    	/*
    	<DTOTransactionInfo id="TransactionInfo-1025730275-1597497456" 
    		TransactionCd="Renewal" 
    		TransactionEffectiveDt="20130430" 
    		TransactionShortDescription="New Business" 
    		SourceCd="Innovation" PaymentTypeCd="None">
			<ElectronicPaymentSource id="ElectronicPaymentSource-1299188198-1989804737" SourceTypeCd="PremiumSource"/>
		</DTOTransactionInfo>
		
    	 */
    	DTOTransactionInfo dtoTransactionInfo = new DTOTransactionInfo();
    	app.setDTOTransactionInfo(dtoTransactionInfo);
    	
    	basicPolicy.setTransactionCd(TRANS_CODE_NEW_BUSINESS);
    	dtoTransactionInfo.setTransactionCd(basicPolicy.getTransactionCd());
    	dtoTransactionInfo.setTransactionShortDescription(app.getDescription());
    	dtoTransactionInfo.setTransactionEffectiveDt(basicPolicy.getEffectiveDt());
    	dtoTransactionInfo.setBookDt(dtoTransactionInfo.getTransactionEffectiveDt());
    	
    	// New Business, Endorsement, Cancellation Notice, Cancellation, etc
    	// record_status IN('Lapsed','Policy','Cancelled')
    	
    	//System.out.println(policyData.POLICYNUMBER + "--->" + policyData.record_status);
    	
    	if(POLICY_RECORD_STATUS_CANCELLED.equalsIgnoreCase(policyData.record_status)){
    		dtoTransactionInfo.setTransactionCd(TRANS_CODE_CANCELLATION);
    		basicPolicy.setTransactionCd(dtoTransactionInfo.getTransactionCd());
    		//basicPolicy.setTransactionNumber(new BigInteger(String.valueOf(basicPolicy.getTransactionNumber().intValue() + 1)));
    		dtoTransactionInfo.setTransactionShortDescription(basicPolicy.getTransactionCd());
    		
    		// 12052014: Import change
        	//basicPolicy.setFullTermAmt(0D);
    		//basicPolicy.setFinalPremiumAmt(0D);
    		//basicPolicy.setCommission(0D);
    		
    		// 01282015: Cancellations will have this as negative.
    		// 02112015: Another brilliant change by master Z.
    		basicPolicy.setConversionWrittenPremiumAmt(policyData.getCONVERSION_WRITTEN_PREMIUM_AMT_FOR_CANCEL() != null ? policyData.getCONVERSION_WRITTEN_PREMIUM_AMT_FOR_CANCEL().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() : 0D);
        	
    		dtoTransactionInfo.setCancelRequestedByCd(policyData.getCancelRequestedByCd());
    		dtoTransactionInfo.setCancelTypeCd(policyData.getCancelTypeCd());
    		
    		synchronized (DATE_FRMT) {
	    		if(policyData.getCANCELLATIONDATE() != null){
	    			dtoTransactionInfo.setTransactionEffectiveDt(DATE_FRMT.format(policyData.getCANCELLATIONDATE()));
	    			dtoTransactionInfo.setBookDt(dtoTransactionInfo.getTransactionEffectiveDt());
				}
	    		if(policyData.getCANCELLATION_TRANSACTION_DATE() != null){
	    			dtoTransactionInfo.setBookDt(DATE_FRMT.format(policyData.getCANCELLATION_TRANSACTION_DATE()));
				}
			}
    		
    		generated.DTOTransactionReason transReason = new generated.DTOTransactionReason();
    		dtoTransactionInfo.setDTOTransactionReason(transReason);
    		transReason.setId(basicPolicy.getPolicyNumber() + "-DTOTransactionInfo-" + dtoTransactionInfo.getTransactionCd() + "-DTOTransactionReason");
    		transReason.setReasonCd(TRANS_REASON_CD_INSURED_REQUEST);
    	}
    	// Hack since first -00 transaction can be lapsed, not policy.
    	else if(LAPSED_RECORD_STATUS_POLICY.equalsIgnoreCase(policyData.record_status) || POLICY_RECORD_STATUS_POLICY.equalsIgnoreCase(policyData.record_status)){
    		if(renewalPolicy){ // policyData.getPOLICYENDDATE().after(new Date()) && 
    			
    			dtoTransactionInfo.setTransactionCd(TRANS_CODE_RENEWAL);
    			basicPolicy.setTransactionCd(dtoTransactionInfo.getTransactionCd());
    			dtoTransactionInfo.setTransactionShortDescription(basicPolicy.getTransactionCd());
    			dtoTransactionInfo.setRewriteToProductVersion(basicPolicy.getProductVersionIdRef());
    			
    			// Set 1 day off just in case.
    			/*
    			synchronized (DATE_FRMT) {
    	    		if(policyData.getPOLICYSTARTDATE() != null){
    	    			Date policyStartDate = DateUtils.addDays(policyData.getPOLICYSTARTDATE(), -1);
    	    			dtoTransactionInfo.setTransactionEffectiveDt(DATE_FRMT.format(policyStartDate));
    	    			dtoTransactionInfo.setBookDt(dtoTransactionInfo.getTransactionEffectiveDt());
    				}
    			}*/
    			
    			// Set renewed from policy to the previously lapsed policy.
    			if(previousApp != null){
    				basicPolicy.setRenewedFromPolicyNumber(previousApp.getDTOBasicPolicy().getPolicyDisplayNumber());
    				
    				// Set renewal cap factor only if value exists in previous application data and it is not 0, otherwise leave default (1.00).
    				if(previousPolicyData != null && previousPolicyData.getRenewalCapFactor() != null){
    					
    					double renewalCapFactor = previousPolicyData.getRenewalCapFactor().setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
    					if(renewalCapFactor > 0D){
    						basicPolicy.setRenewalCapFactor(renewalCapFactor);
    					}
    				}
    				
    				// Reset IDs to previous ID.
    				basicPolicy.setId(previousApp.getDTOBasicPolicy().getId());
    				
    				/*if(rewrites != null){
	    				for (routines.LegacyRewrite rewrite : rewrites) {
	    					if(rewrite.getWRITTEN_PREMIUM() != null){
	    						basicPolicy.setWrittenPremiumAmt(rewrite.getWRITTEN_PREMIUM().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	    					}
	    					if(rewrite.getFINAL_PREMIUM() != null){
	    						basicPolicy.setFinalPremiumAmt(rewrite.getFINAL_PREMIUM().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	    					}
						}
    				}*/
    			}
    			/*else{
    				basicPolicy.setRenewedFromPolicyNumber(basicPolicy.getPolicyNumber() + (((policySeq-1) < 10) ? "-0" : "-") + (policySeq-1));
    			}*/
    		}
    	}
    	//else if("Lapsed".equalsIgnoreCase(policyData.record_status)){
    	//	dtoTransactionInfo.setTransactionCd("Expire");
    	//}
    	
    	dtoTransactionInfo.setId(basicPolicy.getId() + "-DTOTransactionInfo-" + dtoTransactionInfo.getTransactionCd());
    	
    	dtoTransactionInfo.setSourceCd(TRANSACTION_SOURCE_CODE);
    	dtoTransactionInfo.setPaymentTypeCd(PAYMENT_TYPE_CD_NONE);
    	dtoTransactionInfo.setTransactionUser(USER_CONVERSION);
    	
    	// Add credit card info.
    	/*
    	 <ElectronicPaymentSource id="ElectronicPaymentSource-402395869-641365880" SourceTypeCd="InstallmentSource" MethodCd="Credit Card" CreditCardNumber="" CustomerProfileId="17877164" CustomerPaymentProfileId="18058717" />
    	 */
    	if(policyData.getAUTHORIZE_NET_PROFILE_ID() != null && policyData.getAUTHORIZE_NET_PROFILE_PAYMENT_ID() != null){
    		ElectronicPaymentSource eSource = new ElectronicPaymentSource();
    		eSource.setCustomerPaymentProfileId(policyData.getAUTHORIZE_NET_PROFILE_PAYMENT_ID());
    		eSource.setCustomerProfileId(policyData.getAUTHORIZE_NET_PROFILE_ID());
    		eSource.setSourceTypeCd(INSTALLMENT_SOURCE_ELECTRONIC_PAYMENT_SOURCE_TYPE);
    		eSource.setMethodCd(CREDIT_CARD_ELECTRONIC_PAYMENT_METHOD);
    		eSource.setCreditCardNumber("4111111111111111");
    		eSource.setId(basicPolicy.getId() + "-ePaymentSource-" + eSource.getMethodCd());
    		basicPolicy.setElectronicPaymentSource(eSource);
    	}
    
    	// Add insured party info.
    	mapInsured(policyData, app);
    	
    	// Specify renewal discount.
		if(capDiffAmount != null){
			capDiffAmount = capDiffAmount.negate();
    		dtoTransactionInfo.setRenewalDiscountAmt(capDiffAmount.doubleValue());
    	}
    	
    	return app;
    }
    
    /**
     * @param activeOnly
     * @param policyData
     * @param previousPolicyData
     * @param previousApp
     * @return
     */
    public static boolean isRenewal(boolean activeOnly, LegacyPolicyData policyData, LegacyPolicyData previousPolicyData, DTOApplication previousApp){
    	//!policyData.POLICYNUMBER.endsWith("-00") && 
    	boolean renewalPolicy = !activeOnly && (previousApp != null && previousApp.getExternalStateData().equalsIgnoreCase(policyData.TRANSACTION_GROUP.toString()));
    	
    	// Check if policy for the same pet.
    	if(renewalPolicy){
    		if(previousPolicyData != null && previousPolicyData.getPETNAME() != null){
				renewalPolicy = previousPolicyData.getPETNAME().equalsIgnoreCase(policyData.getPETNAME());
			}
			else{
				renewalPolicy = false;
			}
    	}
    	
    	/*if(renewalPolicy){
		basicPolicy.setTransactionNumber(new BigInteger(previousApp.getDTOBasicPolicy().getTransactionNumber().toString()).add(BigInteger.ONE));
		}*/
    	if(renewalPolicy){
    		if(previousPolicyData != null && previousPolicyData.getPOLICYENDDATE() != null && policyData.getPOLICYSTARTDATE() != null){
    			long endTime = policyData.getPOLICYSTARTDATE().getTime();
    			long startTime = previousPolicyData.getPOLICYENDDATE().getTime();
    			long diffTime = endTime - startTime;
    			long diffDays = diffTime / (1000 * 60 * 60 * 24);
    			
    			//System.out.println("Diff Days=" + diffDays);
    			renewalPolicy = !(diffDays > 1);
    		}
    	}
    	return renewalPolicy;
    }
 
    /**
     * @param app
     */
    public static void setCancellationPremiums(DTOApplication app){
    	DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
    	DTOLine line = app.getDTOLine();
    	if(line == null) return;
    	
    	// Negate
    	double amount = 0D;
    	if(basicPolicy.getWrittenPremiumAmt() != null){
    		amount = basicPolicy.getWrittenPremiumAmt() * -1D;
    	}
		
    	// 12052014: Import change
    	//basicPolicy.setWrittenPremiumAmt(amount);
		
    	// 01262015: Import change
		//line.setWrittenPremiumAmt(amount);
		
		DTORisk risk = line.getDTORisk();
		// 01262015: Import change
		//risk.setWrittenPremiumAmt(amount);
		
		List<DTOCoverage> coverages = risk.getDTOCoverages();
		for (DTOCoverage dtoCoverage : coverages) {
			/*
			Find the DTOCoverage with the CoverageCd of VET.  
			Set PrevFinalAmt, and PrevWrittenAmt to the previous written amount.  
			Set FinalPremiumAmt  and FullTermAmt to 0.00.  
			Set the WrittenPremumAmt to the same negated value used in the DTOLine and DTORisk.  
			 */
			if(COVERAGE_CD_VET.equalsIgnoreCase(dtoCoverage.getCoverageCd())){
				//double posAmount = amount * -1D;
				
				// 01262015: Import change
				//dtoCoverage.setPrevFinalAmt(posAmount);
				
				// 01262015: Import change
				//dtoCoverage.setPrevWrittenAmt(posAmount);
				
				// 12052014: Import change
		    	//dtoCoverage.setWrittenPremiumAmt(amount);
			}
			
			// On all other DTOCoverages set FinalPremiumAmt and FullTermAmt to 0.00
			
			// 12052014: Import change
	    	//dtoCoverage.setFullTermAmt(0D);
			//dtoCoverage.setFinalPremiumAmt(0D);
		}
    }
    
    /**
     * @param app
     * @param taxesByPolicyNumbers
     */
    private static void reverseCancellationPremiums(DTOApplication app, Map<String, java.math.BigDecimal> taxesByPolicyNumbers){
    	DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
    	
    	BigDecimal taxesCharged = taxesByPolicyNumbers.get(basicPolicy.getPolicyDisplayNumber());
    	
    	// Negate
    	double amount = 0D;
    	if(basicPolicy.getWrittenPremiumAmt() != null){
    		amount = basicPolicy.getWrittenPremiumAmt() * -1D;
    	}
    	
    	/*if(taxesCharged != null){
    		amount -= taxesCharged.doubleValue();
    		System.out.println(basicPolicy.getFullTermAmt().doubleValue() + "-" + taxesCharged.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "=" + amount);
    	}*/
    	
    	// Reset premiums.
    	double newAmount = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
    	// 12052014: Import change
    	//basicPolicy.setFullTermAmt(newAmount);
    	//basicPolicy.setWrittenPremiumAmt(newAmount);
    	//basicPolicy.setFinalPremiumAmt(newAmount);
    	
		DTOLine line = app.getDTOLine();
		line.setWrittenPremiumAmt(null);
		
		DTORisk risk = line.getDTORisk();
		risk.setWrittenPremiumAmt(null);
		
		List<DTOCoverage> coverages = risk.getDTOCoverages();
		for (DTOCoverage dtoCoverage : coverages) {
			/*
			Find the DTOCoverage with the CoverageCd of VET.  
			Set PrevFinalAmt, and PrevWrittenAmt to the previous written amount.  
			Set FinalPremiumAmt  and FullTermAmt to 0.00.  
			Set the WrittenPremumAmt to the same negated value used in the DTOLine and DTORisk.  
			 */
			if(COVERAGE_CD_VET.equalsIgnoreCase(dtoCoverage.getCoverageCd())){
				dtoCoverage.setPrevFinalAmt(null);
				dtoCoverage.setPrevWrittenAmt(null);
				
				// 12052014: Import change
		    	//dtoCoverage.setWrittenPremiumAmt(null);
				//dtoCoverage.setFullTermAmt(basicPolicy.getWrittenPremiumAmt());
				//dtoCoverage.setFinalPremiumAmt(basicPolicy.getWrittenPremiumAmt());
			}
			// On all other DTOCoverages set FinalPremiumAmt and FullTermAmt to 0.00
			//dtoCoverage.setFullTermAmt(0D);
			//dtoCoverage.setFinalPremiumAmt(0D);
		}
    }
    
    /**
     * @param app
     */
    public static void applyNotActivePolicyRules(DTOApplication app){
    	//DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
		DTOLine line = app.getDTOLine();
		DTORisk risk = line.getDTORisk();
		List<DTOCoverage> coverages = risk.getDTOCoverages();
		for (DTOCoverage dtoCoverage : coverages) {
			if(!COVERAGE_CD_VET.equalsIgnoreCase(dtoCoverage.getCoverageCd())){
				// 12052014: Import change
		    	//dtoCoverage.setFullTermAmt(0D);
				//dtoCoverage.setFinalPremiumAmt(0D);
			}
		}
    }
    
    /**
     * @param taxesByPolicyNumbers
     * @param policyData
     * @param previousPolicyData
     * @param canceledApp
     * @param previousApp
     * @param activeOnly
     */
    public static void convertCanceledToRenewedOrNew(Map<String, java.math.BigDecimal> taxesByPolicyNumbers, routines.LegacyPolicyData policyData, routines.LegacyPolicyData previousPolicyData, DTOApplication canceledApp, DTOApplication previousApp, boolean activeOnly){
    	DTOApplication dtoApplication = canceledApp;
    	
    	reverseCancellationPremiums(dtoApplication, taxesByPolicyNumbers);
    	
    	DTOBasicPolicy basicPolicy = dtoApplication.getDTOBasicPolicy();
    	//basicPolicy.setTransactionNumber(new BigInteger("1"));
    	
    	// 12052014: Import change
    	//basicPolicy.setFullTermAmt(basicPolicy.getWrittenPremiumAmt());
		//basicPolicy.setFinalPremiumAmt(basicPolicy.getFullTermAmt());
		//basicPolicy.setCommission(policyData.getPETPLAN_COMMISSION() != null ? policyData.getPETPLAN_COMMISSION().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() : 0D);
    	
    	// 01282015: Converted cancellation to new/renewal policy needs this reset to 0 in case of v00 version.
    	if(!"v00-00-00".equalsIgnoreCase(policyData.getProductVersionIDRef())){
    		basicPolicy.setConversionWrittenPremiumAmt(0D);
    	}
    	else{
    		basicPolicy.setConversionWrittenPremiumAmt(policyData.getCONVERSION_WRITTEN_PREMIUM_AMT() != null ? policyData.getCONVERSION_WRITTEN_PREMIUM_AMT().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() : 0D);
    	}
    	
    	String displayPolicyNumber = basicPolicy.getPolicyDisplayNumber();
    	
    	boolean renewalPolicy = isRenewal(activeOnly, policyData, previousPolicyData, previousApp);
    	
    	int policySeq = Integer.parseInt(displayPolicyNumber.substring(displayPolicyNumber.length() - 2));
    	
    	DTOTransactionInfo dtoTransactionInfo = dtoApplication.getDTOTransactionInfo();
    	dtoTransactionInfo.setCancelTypeCd(null);
    	dtoTransactionInfo.setCancelRequestedByCd(null);
    	dtoTransactionInfo.setDTOTransactionReason(null);
    	dtoTransactionInfo.setTransactionEffectiveDt(basicPolicy.getEffectiveDt());
    	dtoTransactionInfo.setBookDt(dtoTransactionInfo.getTransactionEffectiveDt());
    	
    	generated.DTOTransactionReason transReason = dtoTransactionInfo.getDTOTransactionReason();
    	if(transReason != null){
    		transReason.setId(transReason.getId() + "-" + RandomStringUtils.randomAlphanumeric(7));
    	}
    	
    	basicPolicy.setTransactionCd(TRANS_CODE_NEW_BUSINESS);
    	dtoTransactionInfo.setTransactionCd(basicPolicy.getTransactionCd());
    	dtoTransactionInfo.setTransactionShortDescription(dtoApplication.getDescription());
    	
    	if(renewalPolicy){
    		dtoTransactionInfo.setTransactionCd(TRANS_CODE_RENEWAL);
    		basicPolicy.setTransactionCd(dtoTransactionInfo.getTransactionCd());
    		dtoTransactionInfo.setTransactionShortDescription(basicPolicy.getTransactionCd());
    		dtoTransactionInfo.setRewriteToProductVersion(basicPolicy.getProductVersionIdRef());
    		
    		// Set renewed from policy to the previously lapsed policy.
    		if(previousApp != null){
				basicPolicy.setRenewedFromPolicyNumber(previousApp.getDTOBasicPolicy().getPolicyDisplayNumber());
			}
			else{
				basicPolicy.setRenewedFromPolicyNumber(basicPolicy.getPolicyNumber() + (((policySeq-1) < 10) ? "-0" : "-") + (policySeq-1));
			}
    	}
    	
    	dtoTransactionInfo.setId(basicPolicy.getPolicyNumber() + "-DTOTransactionInfo-" + dtoTransactionInfo.getTransactionCd());
    }
    
    /**
     * @param policyData
     * @param dtoApplication
     */
    public static void mapInsured(LegacyPolicyData policyData, DTOApplication dtoApplication){
    	DTOBasicPolicy basicPolicy = dtoApplication.getDTOBasicPolicy();
    	
    	/*
    	<DTOInsured id="Insured-1724674263-2013554682" IndexName="FEDOROV, MAXIM" EntityTypeCd="Individual" PreferredDeliveryMethod="Email">
			<PartyInfo id="PartyInfo-1766230948-377090555" PartyTypeCd="InsuredParty">
				<PersonInfo id="PersonInfo-518771179-203598633" PersonTypeCd="InsuredPersonal"/>
				<EmailInfo id="EmailInfo-940775160-1833504906" EmailTypeCd="InsuredEmail" EmailAddr="mfedorov@netflexity.com" PreferredInd="No"/>
				<TaxInfo id="TaxInfo-660305905-1713341286" TaxTypeCd="InsuredTaxInfo"/>
				<PhoneInfo id="PhoneInfo-524639083-41213399" PhoneTypeCd="InsuredPhonePrimary" PhoneNumber="(267) 408-3699" PreferredInd="No" PhoneName="Mobile"/>
				<NameInfo id="NameInfo-536715588-1147570116" NameTypeCd="InsuredName" GivenName="MAXIM" Surname="FEDOROV" CommercialName="FEDOROV, MAXIM" PrefixCd="Mr."/>
				<Addr id="Addr-1566794314-667555884" AddrTypeCd="InsuredMailingAddr" Addr1="55 KASI CIRCLE" City="IVYLAND" StateProvCd="PA" PostalCode="18974"/>
				<EmailInfo id="EmailInfo-1474104339-1900273781" EmailTypeCd="InsuredEmailSecondary" PreferredInd="No"/>
				<PhoneInfo id="PhoneInfo-1183862659-743720358" PhoneTypeCd="InsuredPhoneSecondary" PhoneNumber="(215) 825-8418" PreferredInd="No" PhoneName="Home"/>
				<PhoneInfo id="PhoneInfo-1353231366-1907746632" PhoneTypeCd="InsuredFax" PhoneNumber="(267) 408-3699" PreferredInd="No"/>
				<Addr id="Addr-1157170460-952013988" AddrTypeCd="InsuredBillingAddr" Addr1="55 KASI CIRCLE" City="IVYLAND" StateProvCd="PA" PostalCode="18974"/>
				<Addr id="Addr-1745926166-564519354" AddrTypeCd="InsuredLookupAddr"/>
			</PartyInfo>
    	 */
    	
    	DTOInsured dtoInsured = new DTOInsured();
    	dtoApplication.setDTOInsured(dtoInsured);
    	
    	dtoInsured.setIndexName(policyData.getSURNAME() + ", " + policyData.getFORENAME());
    	dtoInsured.setEntityTypeCd(getDTOInsuredEntityTypeCd(policyData));
    	dtoInsured.setPreferredDeliveryMethod(getPreferredDeliveryMethod(policyData));
    	//dtoInsured.setPassword(policyData.getPASSWORD());
    	dtoInsured.setId(basicPolicy.getId() + "-Insured-" + dtoInsured.getEntityTypeCd());
    	
    	PartyInfo partyInfo = new PartyInfo();
    	dtoInsured.getPartyInfo().add(partyInfo);
    	partyInfo.setId(dtoInsured.getId() + "-PartyInfo-1");
    	partyInfo.setPartyTypeCd(PARTY_TYPE_CD_INSURED_PARTY);
    	
    	PersonInfo personInfo = new PersonInfo();
    	personInfo.setPersonTypeCd(getPersonTypeCode(policyData));
    	personInfo.setId(partyInfo.getId() + "-PersonInfo-" + personInfo.getPersonTypeCd());
    	partyInfo.setPersonInfo(personInfo);
    	
    	EmailInfo emailInfo = new EmailInfo();
    	emailInfo.setEmailTypeCd(EMAIL_TYPE_CODE_INSURED_EMAIL);
    	emailInfo.setPreferredInd(INDICATOR_YES);
    	emailInfo.setEmailAddr(policyData.getEMAIL());
    	emailInfo.setId(partyInfo.getId() + "-EmailInfo-" + emailInfo.getEmailTypeCd());
    	partyInfo.getEmailInfo().add(emailInfo);
    	
    	EmailInfo emailInfo2 = new EmailInfo();
    	emailInfo2.setEmailTypeCd(EMAIL_TYPE_CODE_INSURED_EMAIL_SECONDARY);
    	emailInfo2.setId(partyInfo.getId() + "-EmailInfo-" + emailInfo2.getEmailTypeCd());
    	partyInfo.getEmailInfo().add(emailInfo2);
    	
    	NameInfo nameInfo = new NameInfo();
    	nameInfo.setNameTypeCd(NAME_TYPE_CODE_INSURED_NAME);
    	nameInfo.setGivenName(policyData.getFORENAME());
    	nameInfo.setSurname(policyData.getSURNAME());
    	nameInfo.setCommercialName(dtoInsured.getIndexName());
    	nameInfo.setId(partyInfo.getId() + "-NameInfo-" + nameInfo.getNameTypeCd());
    	partyInfo.setNameInfo(nameInfo);
    	
    	Addr addr = new Addr();
    	addr.setAddrTypeCd(ADDR_TYPE_CODE_INSURED_MAILING_ADDR);
    	addr.setAddr1(policyData.getSTREET2());
    	addr.setCity(policyData.getCITY2());
    	addr.setStateProvCd(policyData.getCOUNTY2());
    	addr.setPostalCode(policyData.getPOSTCODE2());
    	addr.setId(partyInfo.getId() + "-Addr-" + addr.getAddrTypeCd());
    	processAddr(addr);
    	partyInfo.getAddrs().add(addr);
    	
    	addr = new Addr();
    	addr.setAddrTypeCd(ADDR_TYPE_CODE_INSURED_BILLING_ADDR);
    	addr.setAddr1(policyData.getSTREET2());
    	addr.setCity(policyData.getCITY2());
    	addr.setStateProvCd(policyData.getCOUNTY2());
    	addr.setPostalCode(policyData.getPOSTCODE2());
    	addr.setId(partyInfo.getId() + "-Addr-" + addr.getAddrTypeCd());
    	processAddr(addr);
    	partyInfo.getAddrs().add(addr);
    	
    	//PreDirectional="Ne" PrimaryNumber="4227" StreetName="10th" Suffix
    	addr = new Addr();
    	addr.setAddrTypeCd(ADDR_TYPE_CODE_INSURED_LOOKUP_ADDR);
    	//String addressParts[] = policyData.getSTREET().split(" ");
    	//parseAddress(addressParts, addr);
    	addr.setAddr1(policyData.getSTREET());
    	addr.setCity(policyData.getCITY());
    	addr.setStateProvCd(policyData.getCOUNTY());
    	addr.setPostalCode(policyData.getPOSTCODE());
    	addr.setId(partyInfo.getId() + "-Addr-" + addr.getAddrTypeCd());
    	processAddr(addr);
    	partyInfo.getAddrs().add(addr);
    	
    	TaxInfo taxInfo = new TaxInfo();
    	taxInfo.setTaxTypeCd(TAX_TYPE_CODE_INSURED_TAX_INFO);
    	taxInfo.setTaxIdTypeCd(TAX_ID_TYPE_CODE_SSN);
    	taxInfo.setId(partyInfo.getId() + "-TaxInfo-" + taxInfo.getTaxIdTypeCd());
    	partyInfo.setTaxInfo(taxInfo);
    	
    	PartyInfo jointParty = new PartyInfo();
        jointParty.setPartyTypeCd("InsuredPartyJoint");
        jointParty.setId(dtoInsured.getId() + "-PartyInfo-" + jointParty.getPartyTypeCd());
        dtoInsured.getPartyInfo().add(jointParty);
        
        personInfo = new PersonInfo();
    	personInfo.setPersonTypeCd("InsuredPersonalJoint");
    	personInfo.setId(jointParty.getId() + "-PersonInfo-" + personInfo.getPersonTypeCd());
    	jointParty.setPersonInfo(personInfo);
    	
    	taxInfo = new TaxInfo();
    	taxInfo.setTaxTypeCd("InsuredTaxInfoJoint");
    	taxInfo.setId(jointParty.getId() + "-TaxInfo-" + taxInfo.getTaxTypeCd());
    	jointParty.setTaxInfo(taxInfo);
    	
    	nameInfo = new NameInfo();
    	nameInfo.setNameTypeCd("InsuredNameJoint");
    	nameInfo.setId(jointParty.getId() + "-NameInfo-" + nameInfo.getNameTypeCd());
    	jointParty.setNameInfo(nameInfo);
    	
    	PartyInfo trustParty = new PartyInfo();
    	trustParty.setPartyTypeCd("InsuredPartyTrust");
    	trustParty.setId(dtoInsured.getId() + "-PartyInfo-" + trustParty.getPartyTypeCd());
    	dtoInsured.getPartyInfo().add(trustParty);
    	
    	taxInfo = new TaxInfo();
    	taxInfo.setTaxTypeCd("InsuredTaxInfoTrust");
    	taxInfo.setId(trustParty.getId() + "-TaxInfo-" + taxInfo.getTaxTypeCd());
    	trustParty.setTaxInfo(taxInfo);
	    
    	PartyInfo estateParty = new PartyInfo();
    	estateParty.setPartyTypeCd("InsuredPartyEstate");
    	estateParty.setId(dtoInsured.getId() + "-PartyInfo-" + estateParty.getPartyTypeCd());
    	dtoInsured.getPartyInfo().add(estateParty);
    	
    	taxInfo = new TaxInfo();
    	taxInfo.setTaxTypeCd("InsuredTaxInfoEstate");
    	taxInfo.setId(estateParty.getId() + "-TaxInfo-" + taxInfo.getTaxTypeCd());
    	estateParty.setTaxInfo(taxInfo);
    }
    
    public static void processAddr(Addr addr){
    	Addr temp = copyAddr(addr);
        if (hasParsedData(temp)) {
        	convertParsedToUnparsed(temp, false);
        	clearParsedFields(temp);
        }
        
        addr.setVerificationHash(getAddressHash(temp));
    }
    
    /**
     * @param partyInfo
     * @param orig
     * @return
     */
    public static Addr copyAddress(PartyInfo partyInfo, Addr orig){
    	Addr addr = copyAddr(orig);
    	addr.setId(partyInfo.getId() + "-Addr-" + addr.getAddrTypeCd());
    	processAddr(addr);
    	return addr;
    }
    
    /**
     * @param policyData
     * @param dtoApplication
     * @param questions
     */
    public static void mapQuestionReplies(LegacyPolicyData policyData, DTOApplication dtoApplication, Questions questions){
    	DTOBasicPolicy policy = dtoApplication.getDTOBasicPolicy();
    	if(questions != null){
    		QuestionReplies replies = new QuestionReplies();
    		replies.setId(dtoApplication.getDTOBasicPolicy().getId() + "-QuestionReplies");
    		dtoApplication.setQuestionReplies(replies);
    		//replies.setId("QuestionReplies-" + RANDOM.nextInt() + "-" + RANDOM.nextInt());
    		replies.setQuestionSourceMDA("UWProduct::product-master::" + policy.getProductVersionIdRef() + ":://ProductSetup[@id='ProductSetup']");
    		
    		// Main question groups
    		int i = 0;
    		for (generated.Question q : questions.getQuestion()) {
    			i++;
    			// Subgroups
    			for (generated.Questions qs : q.getQuestions()) {
    				// Subgroup questions
    				for (generated.Question q2 : qs.getQuestion()) {
    					i++;
    					QuestionReply reply = new QuestionReply();
    					reply.setId(replies.getId() + "-QuestionReply-SequenceNumber-" + i);
    					reply.setName(q2.getName());
    					
    					if(INDICATOR_YES.equalsIgnoreCase(q2.getRequiredInd())
    							&& !(q2.getName().startsWith("MedicalServices") && !policyData.getPETMEDSERV())){
    						
    						String dataType = q2.getDataType();
    						if("List".equalsIgnoreCase(dataType)){
    							String[] constraints = q2.getConstraints().split(",");
    							reply.setValue(constraints[0]);
    						}
    						else if("YesNo".equalsIgnoreCase(dataType)){
    							if(q2.getName().startsWith("PreviousIllness")){
    								reply.setValue(INDICATOR_NO.toUpperCase());
    							}
    							else{
    								reply.setValue(INDICATOR_YES.toUpperCase());
    							}
    						}
    						else if("Date".equalsIgnoreCase(dataType)){
    							Calendar cal = Calendar.getInstance();
    							
    							// Must be before policy effective date.
    							if("LastExam".equalsIgnoreCase(q2.getName())){
    								synchronized (DATE_FRMT) {
    									try {
											cal.setTime(DATE_FRMT.parse(policy.getEffectiveDt()));
											cal.add(Calendar.DAY_OF_MONTH, -5);
										} catch (ParseException e) {
										}
    								}
    							}
    							else{ 
    								// Must be before policy effective date.
        							if("ObtainDate".equalsIgnoreCase(q2.getName())){
        								synchronized (DATE_FRMT) {
        									try {
    											cal.setTime(DATE_FRMT.parse(policy.getEffectiveDt()));
    											cal.add(Calendar.YEAR, -1);
    										} catch (ParseException e) {
    										}
        								}
        							}
    							}
    							
    							synchronized (DATE_FRMT) {
    								reply.setValue(DATE_FRMT.format(cal.getTime()));
								}
    						}
    						else if("Numeric".equalsIgnoreCase(dataType)){
    							reply.setValue("1");
    						}
    						else{
    							reply.setValue("N/A");
    						}
    						
    						reply.setVisibleInd(INDICATOR_YES);
    					}
    					else{
    						reply.setVisibleInd(INDICATOR_NO);
    					}
    					replies.getQuestionReply().add(reply);
    				}
    			}
			}
    	}
    }
    
    /**
     * @param dtoApplication
     * @param TELEPHONENUMBER
     * @param TELEPHONE_TYPE_ID
     * @param TELEPHONE_TYPE_DEBUG
     */
    public static void mapTelephone(DTOApplication dtoApplication, String TELEPHONENUMBER, String TELEPHONE_TYPE_ID, String TELEPHONE_TYPE_DEBUG){
    	if(dtoApplication.getDTOInsured() == null || dtoApplication.getDTOInsured().getPartyInfo() == null) return;
    	
    	boolean primarySetFlag = false;
    	boolean secondarySetFlag = false;
    	boolean faxSetFlag = false;
    	List<PhoneInfo> phoneInfos = getPartyInfo(dtoApplication.getDTOInsured(), PARTY_TYPE_CD_INSURED_PARTY).getPhoneInfos();
    	for (PhoneInfo phoneInfo : phoneInfos) {
    		if(phoneInfo.getPhoneTypeCd().equalsIgnoreCase(PHONE_TYPE_CODE_INSURED_PHONE_PRIMARY)){
    		    primarySetFlag = true;
    		}
    		else if(phoneInfo.getPhoneTypeCd().equalsIgnoreCase(PHONE_TYPE_CODE_INSURED_PHONE_SECONDARY)){
    			secondarySetFlag = true;
    		}
    		else if(phoneInfo.getPhoneTypeCd().equalsIgnoreCase(PHONE_TYPE_CODE_INSURED_FAX)){
    			faxSetFlag = true;
    		}
		}
    	
    	PhoneInfo phoneInfo = new PhoneInfo();
    	phoneInfo.setPhoneNumber(TELEPHONENUMBER);
    	phoneInfo.setPhoneName(TELEPHONE_TYPE_DEBUG);
    	phoneInfos.add(phoneInfo);
    	
    	
    	if("Home".equalsIgnoreCase(TELEPHONE_TYPE_DEBUG) && !primarySetFlag){
			phoneInfo.setPhoneTypeCd(PHONE_TYPE_CODE_INSURED_PHONE_PRIMARY);
		    phoneInfo.setPreferredInd(INDICATOR_YES);
		}
		else if("Cell".equalsIgnoreCase(TELEPHONE_TYPE_DEBUG) && !secondarySetFlag){
			phoneInfo.setPhoneTypeCd(PHONE_TYPE_CODE_INSURED_PHONE_SECONDARY);
			phoneInfo.setPreferredInd(INDICATOR_NO);
		}
		else if("Work".equalsIgnoreCase(TELEPHONE_TYPE_DEBUG) && !faxSetFlag){
			phoneInfo.setPhoneTypeCd(PHONE_TYPE_CODE_INSURED_FAX);
			phoneInfo.setPreferredInd(INDICATOR_NO);
		}
    	
    	phoneInfo.setId(getPartyInfo(dtoApplication.getDTOInsured(), PARTY_TYPE_CD_INSURED_PARTY).getId() + "-PhoneInfo-" + phoneInfo.getPhoneTypeCd());
    }
    
    /**
     * @param partyInfo
     * @param orig
     * @return
     */
    public static PhoneInfo copyPhone(PartyInfo partyInfo, PhoneInfo orig){
    	PhoneInfo phoneInfo = new PhoneInfo();
    	phoneInfo.setPhoneNumber(orig.getPhoneNumber());
    	phoneInfo.setPhoneName(orig.getPhoneName());
    	phoneInfo.setPhoneTypeCd(orig.getPhoneTypeCd());
    	phoneInfo.setPreferredInd(orig.getPreferredInd());
    	
    	phoneInfo.setId(partyInfo.getId() + "-PhoneInfo-" + phoneInfo.getPhoneTypeCd());
    	
    	return phoneInfo;
    }
    
    /**
     * @param partyInfo
     * @param orig
     * @return
     */
    public static EmailInfo copyEmail(PartyInfo partyInfo, EmailInfo orig){
    	EmailInfo emailInfo = new EmailInfo();
    	emailInfo.setEmailAddr(orig.getEmailAddr());
    	emailInfo.setEmailTypeCd(orig.getEmailTypeCd());
    	emailInfo.setPreferredInd(orig.getPreferredInd());
    	
    	emailInfo.setId(partyInfo.getId() + "-EmailInfo-" + emailInfo.getEmailTypeCd());
    	
    	return emailInfo;
    }
    
    /**
     * @param dtoApplication
     * @param TELEPHONENUMBER
     * @param TELEPHONE_TYPE_ID
     * @param TELEPHONE_TYPE_DEBUG
     */
    public static void validatePrimaryTelephone(DTOApplication dtoApplication){
    	if(dtoApplication.getDTOInsured() == null || dtoApplication.getDTOInsured().getPartyInfo() == null) return;
    	
    	boolean primarySetFlag = false;
    	boolean secondarySetFlag = false;
    	boolean faxSetFlag = false;
    	List<PhoneInfo> phoneInfos = getPartyInfo(dtoApplication.getDTOInsured(), PARTY_TYPE_CD_INSURED_PARTY).getPhoneInfos();
    	for (PhoneInfo phoneInfo : phoneInfos) {
    		if(phoneInfo.getPhoneTypeCd().equalsIgnoreCase(PHONE_TYPE_CODE_INSURED_PHONE_PRIMARY)){
    		    primarySetFlag = true;
    		}
    		else if(phoneInfo.getPhoneTypeCd().equalsIgnoreCase(PHONE_TYPE_CODE_INSURED_PHONE_SECONDARY)){
    			secondarySetFlag = true;
    		}
    		else if(phoneInfo.getPhoneTypeCd().equalsIgnoreCase(PHONE_TYPE_CODE_INSURED_FAX)){
    			faxSetFlag = true;
    		}
		}
    	
    	PartyInfo insuredPartyInfo = getPartyInfo(dtoApplication.getDTOInsured(), PARTY_TYPE_CD_INSURED_PARTY);
    	if(!primarySetFlag){
    		if(secondarySetFlag){
    			PhoneInfo secondary = getPartyPhone(insuredPartyInfo, PHONE_TYPE_CODE_INSURED_PHONE_SECONDARY);
    			PhoneInfo phoneInfo = new PhoneInfo();
    			phoneInfo.setPhoneNumber(secondary.getPhoneNumber());
    	    	phoneInfo.setPhoneName(secondary.getPhoneName());
    	    	phoneInfo.setPhoneTypeCd(PHONE_TYPE_CODE_INSURED_PHONE_PRIMARY);
    		    phoneInfo.setPreferredInd(INDICATOR_YES);
    		    phoneInfo.setId(insuredPartyInfo.getId() + "-PhoneInfo-" + phoneInfo.getPhoneTypeCd());
    	    	phoneInfos.add(phoneInfo);
    		}
    		else if(faxSetFlag){
    			PhoneInfo fax = getPartyPhone(getPartyInfo(dtoApplication.getDTOInsured(), PARTY_TYPE_CD_INSURED_PARTY), PHONE_TYPE_CODE_INSURED_FAX);
    			PhoneInfo phoneInfo = new PhoneInfo();
    	    	phoneInfo.setPhoneNumber(fax.getPhoneNumber());
    	    	phoneInfo.setPhoneName(fax.getPhoneName());
    	    	phoneInfo.setPhoneTypeCd(PHONE_TYPE_CODE_INSURED_PHONE_PRIMARY);
    		    phoneInfo.setPreferredInd(INDICATOR_YES);
    		    phoneInfo.setId(insuredPartyInfo.getId() + "-PhoneInfo-" + phoneInfo.getPhoneTypeCd());
    	    	phoneInfos.add(phoneInfo);
    		}
    		else{
    			setDefaultPrimaryTelephone(dtoApplication);
    		}
		}
    	if(!secondarySetFlag){
			PhoneInfo phoneInfo = new PhoneInfo();
	    	phoneInfo.setPhoneTypeCd(PHONE_TYPE_CODE_INSURED_PHONE_SECONDARY);
	    	phoneInfo.setId(insuredPartyInfo.getId() + "-PhoneInfo-" + phoneInfo.getPhoneTypeCd());
	    	phoneInfos.add(phoneInfo);
		}
    	/*
    	<PhoneInfo id="PhoneInfo-153312-26589770" PhoneTypeCd="InsuredFax" PreferredInd="No" />
    	*/
    	if(!faxSetFlag){
			PhoneInfo phoneInfo = new PhoneInfo();
	    	phoneInfo.setPhoneTypeCd(PHONE_TYPE_CODE_INSURED_FAX);
	    	phoneInfo.setId(insuredPartyInfo.getId() + "-PhoneInfo-" + phoneInfo.getPhoneTypeCd());
	    	phoneInfo.setPreferredInd(INDICATOR_NO);
	    	phoneInfos.add(phoneInfo);
		}
    }
    
    /**
     * @param dtoApplication
     */
    public static void setDefaultPrimaryTelephone(DTOApplication dtoApplication){
    	if(dtoApplication.getDTOInsured() == null || dtoApplication.getDTOInsured().getPartyInfo() == null) return;
    	
    	if(dtoApplication.getDTOInsured().getPartyInfo() == null) return;
    	PartyInfo insuredPartyInfo = getPartyInfo(dtoApplication.getDTOInsured(), PARTY_TYPE_CD_INSURED_PARTY);
    	List<PhoneInfo> phoneInfos = insuredPartyInfo.getPhoneInfos();
    	
    	PhoneInfo phoneInfo = new PhoneInfo();
    	phoneInfo.setPhoneNumber("215-555-1212");
    	phoneInfo.setPhoneName("DEFAULT");
    	phoneInfo.setPhoneTypeCd(PHONE_TYPE_CODE_INSURED_PHONE_PRIMARY);
	    phoneInfo.setPreferredInd(INDICATOR_YES);
	    phoneInfo.setId(insuredPartyInfo.getId() + "-PhoneInfo-" + phoneInfo.getPhoneTypeCd());
    	phoneInfos.add(phoneInfo);
    	
    }
    
    /**
     * @param dtoApplication
     */
    public static void setDefaultPrimaryTelephone(DTOCustomer dtoCustomer){
    	if(dtoCustomer.getPartyInfo() == null || dtoCustomer.getPartyInfo().isEmpty()) return;
    	PartyInfo customerParty = getPartyInfo(dtoCustomer, PARTY_TYPE_CD_CUSTOMER_PARTY);
    	if(customerParty != null){
	    	List<PhoneInfo> phoneInfos = customerParty.getPhoneInfos();
	    	
	    	PhoneInfo phoneInfo = new PhoneInfo();
	    	phoneInfo.setPhoneNumber("215-555-1212");
	    	phoneInfo.setPhoneName("DEFAULT");
	    	phoneInfo.setPhoneTypeCd(PHONE_TYPE_CODE_CUSTOMER_PHONE_PRIMARY);
		    phoneInfo.setPreferredInd(INDICATOR_YES);
	    	phoneInfos.add(phoneInfo);
	    	
	    	//phoneInfo.setId(dtoCustomer.getPartyInfo().getId() + "-PhoneInfo-" + phoneInfo.getPhoneTypeCd());
    	}
    }
    
    /**
     * @param dtoApplication
     * @param TELEPHONENUMBER
     * @param TELEPHONE_TYPE_ID
     * @param TELEPHONE_TYPE_DEBUG
     */
    public static void mapTelephone(DTOCustomer dtoCustomer, String TELEPHONENUMBER, String TELEPHONE_TYPE_ID, String TELEPHONE_TYPE_DEBUG){
    	if(dtoCustomer.getPartyInfo() == null) return;
    	
    	boolean primarySetFlag = false;
    	boolean secondarySetFlag = false;
    	boolean faxSetFlag = false;
    	PartyInfo customerParty = getPartyInfo(dtoCustomer, PARTY_TYPE_CD_CUSTOMER_PARTY);
    	if(customerParty != null){
	    	List<PhoneInfo> phoneInfos = customerParty.getPhoneInfos();
	    	for (PhoneInfo phoneInfo : phoneInfos) {
	    		if(phoneInfo.getPhoneTypeCd().equalsIgnoreCase(PHONE_TYPE_CODE_CUSTOMER_PHONE_PRIMARY)){
	    		    primarySetFlag = true;
	    		}
	    		else if(phoneInfo.getPhoneTypeCd().equalsIgnoreCase(PHONE_TYPE_CODE_CUSTOMER_PHONE_SECONDARY)){
	    			secondarySetFlag = true;
	    		}
	    		else if(phoneInfo.getPhoneTypeCd().equalsIgnoreCase(PHONE_TYPE_CODE_CUSTOMER_FAX)){
	    			faxSetFlag = true;
	    		}
			}
	    	PhoneInfo phoneInfo = new PhoneInfo();
	    	phoneInfo.setPhoneNumber(TELEPHONENUMBER);
	    	phoneInfo.setPhoneName(TELEPHONE_TYPE_DEBUG);
	    	phoneInfos.add(phoneInfo);
	    	
	    	
	    	if("Home".equalsIgnoreCase(TELEPHONE_TYPE_DEBUG) && !primarySetFlag){
				phoneInfo.setPhoneTypeCd(PHONE_TYPE_CODE_CUSTOMER_PHONE_PRIMARY);
			    phoneInfo.setPreferredInd(INDICATOR_YES);
			}
			else if("Cell".equalsIgnoreCase(TELEPHONE_TYPE_DEBUG) && !secondarySetFlag){
				phoneInfo.setPhoneTypeCd(PHONE_TYPE_CODE_CUSTOMER_PHONE_SECONDARY);
				phoneInfo.setPreferredInd(INDICATOR_NO);
			}
			else if("Work".equalsIgnoreCase(TELEPHONE_TYPE_DEBUG) && !faxSetFlag){
				phoneInfo.setPhoneTypeCd(PHONE_TYPE_CODE_CUSTOMER_FAX);
				phoneInfo.setPreferredInd(INDICATOR_NO);
			}
	    	
	    	//phoneInfo.setId(dtoCustomer.getPartyInfo().getId() + "-PhoneInfo-" + phoneInfo.getPhoneTypeCd());
    	}
    }
    
    /**
     * @param dtoApplication
     * @param TELEPHONENUMBER
     * @param TELEPHONE_TYPE_ID
     * @param TELEPHONE_TYPE_DEBUG
     */
    public static void validatePrimaryTelephone(DTOCustomer dtoCustomer){
    	if(dtoCustomer.getPartyInfo() == null) return;
    	
    	boolean primarySetFlag = false;
    	boolean secondarySetFlag = false;
    	boolean faxSetFlag = false;
    	PartyInfo customerParty = getPartyInfo(dtoCustomer, PARTY_TYPE_CD_CUSTOMER_PARTY);
    	if(customerParty != null){
	    	List<PhoneInfo> phoneInfos = customerParty.getPhoneInfos();
	    	for (PhoneInfo phoneInfo : phoneInfos) {
	    		if(phoneInfo.getPhoneTypeCd().equalsIgnoreCase(PHONE_TYPE_CODE_CUSTOMER_PHONE_PRIMARY)){
	    		    primarySetFlag = true;
	    		}
	    		else if(phoneInfo.getPhoneTypeCd().equalsIgnoreCase(PHONE_TYPE_CODE_CUSTOMER_PHONE_SECONDARY)){
	    			secondarySetFlag = true;
	    		}
	    		else if(phoneInfo.getPhoneTypeCd().equalsIgnoreCase(PHONE_TYPE_CODE_CUSTOMER_FAX)){
	    			faxSetFlag = true;
	    		}
			}
	    	
	    	if(!primarySetFlag){
	    		if(secondarySetFlag){
	    			PhoneInfo secondary = getPartyPhone(customerParty, PHONE_TYPE_CODE_CUSTOMER_PHONE_SECONDARY);
	    			PhoneInfo phoneInfo = new PhoneInfo();
	    	    	phoneInfo.setPhoneNumber(secondary.getPhoneNumber());
	    	    	phoneInfo.setPhoneName(secondary.getPhoneName());
	    	    	phoneInfo.setPhoneTypeCd(PHONE_TYPE_CODE_CUSTOMER_PHONE_PRIMARY);
	    		    phoneInfo.setPreferredInd(INDICATOR_YES);
	    	    	phoneInfos.add(phoneInfo);
	    		}
	    		else if(faxSetFlag){
	    			PhoneInfo fax = getPartyPhone(customerParty, PHONE_TYPE_CODE_CUSTOMER_FAX);
	    			PhoneInfo phoneInfo = new PhoneInfo();
	    	    	phoneInfo.setPhoneNumber(fax.getPhoneNumber());
	    	    	phoneInfo.setPhoneName(fax.getPhoneName());
	    	    	phoneInfo.setPhoneTypeCd(PHONE_TYPE_CODE_CUSTOMER_PHONE_PRIMARY);
	    		    phoneInfo.setPreferredInd(INDICATOR_YES);
	    	    	phoneInfos.add(phoneInfo);
	    		}
	    		else{
	    			setDefaultPrimaryTelephone(dtoCustomer);
	    		}
			}
    	}
    }
    
    /**
     * @param dtoApplication
     * @param coverageData
     * @param policyData
     */
    public static void mapCoverage(DTOApplication dtoApplication, DTOApplication prev_dtoApplication, List<routines.LegacyCoverage> planCovarages, LegacyPolicyData policyData){
    	boolean renewalPolicy = (prev_dtoApplication != null && prev_dtoApplication.getExternalStateData().equalsIgnoreCase(dtoApplication.getExternalStateData()));
    	
    	DTOBasicPolicy policy = dtoApplication.getDTOBasicPolicy();
    	String policyNumber = policy.getPolicyNumber();
    	
    	DTOLine line = new DTOLine();
    	dtoApplication.setDTOLine(line);
    	line.setStatusCd(ACTIVE);
    	line.setLineCd(LINE_CODE_PET);
    	line.setRatingService(RATING_SERVICE_MANUAL_RATE);
    	line.setId(policy.getId() + "-Line-" + line.getLineCd());
    	
    	DTORisk risk = new DTORisk();
    	line.setDTORisk(risk);
    	
    	// Obtain renewal discount if there is one.
    	BigDecimal breedFactor = policyData.getBREED_FACTOR();
    	if(breedFactor != null){
    		risk.setBreedFactor(breedFactor.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
    	}
    	
    	risk.setStatus(line.getStatusCd());
    	risk.setTypeCd(line.getLineCd());
    	risk.setDescription(risk.getTypeCd());
    	risk.setPetType(policyData.getPetType());
    	risk.setPetName(policyData.getPETNAME());
    	risk.setBreed(policyData.getPETBREED_ID()); // policyData.getPETBREED_ID()); // policyData.getPETPLAN_PET_BREED_DEBUG());
    	risk.setBreedType(policyData.getBreedType());
    	synchronized (DATE_FRMT) {
    		if(policyData.getPETDOB() != null){
    			risk.setBirthDt(DATE_FRMT.format(policyData.getPETDOB()));
			}
		}
    	if(policyData.getDeductible() != null){
    		risk.setDeductible(policyData.getDeductible().toString());
    	}
    	else{
    		risk.setDeductible(Integer.toString(0));
    	}
    	if(policyData.getCopay() != null){
    		String copayPct = policyData.getCopay().toString();
    		if("1.0".equals(copayPct)){
    			copayPct = "1";
    		}
    		risk.setCopayPct(copayPct);
    	}
    	else{
    		risk.setCopayPct(Double.toString(0D));
    	}
    	risk.setVetStaffDiscInd(policyData.getVETSTAFF() ? INDICATOR_YES : INDICATOR_NO);
    	risk.setMicrochipDiscInd(policyData.getMICROCHIPPED() ? INDICATOR_YES : INDICATOR_NO);
    	risk.setMedicalServicesDiscInd(policyData.getPETMEDSERV() ? INDICATOR_YES : INDICATOR_NO);
    	risk.setWorkingDogDiscInd(policyData.getWORKINGDOG() == null ? INDICATOR_NO : (policyData.getWORKINGDOG() ? INDICATOR_YES : INDICATOR_NO));
    	risk.setInternetDiscInd(policyData.getWEB_DISCOUNT() == 1 ? INDICATOR_YES : INDICATOR_NO);
    	risk.setPolicyHolderDiscountInd(policyData.getPOLICYHOLDER_DISCOUNT() ? INDICATOR_YES : INDICATOR_NO);
    	risk.setId(line.getId() + "-Risk-" + risk.getTypeCd());
    	
    	//risk.setFullTermAmt(basicPolicy.getFullTermAmt());
    	//risk.setWrittenPremiumAmt(basicPolicy.setWrittenPremiumAmt(value));
    	//risk.setFinalPremiumAmt(basicPolicy.getFinalPremiumAmt());
    	
    	if(planCovarages != null){
	    	for (LegacyCoverage coverageData : planCovarages) {
	    		int index = 1;
	    		
	    		// Remove death coverage from non-qualified pets (cats >= 10 or dogs >=8 or selected dogs >=9) 
	    		if("DEATH".equalsIgnoreCase(coverageData.code) && policyData.REMOVE_DEATH_BENEFIT == 1){
	    			continue;
	    		}
	    		
		    	DTOCoverage coverage = new DTOCoverage();
		    	risk.getDTOCoverages().add(coverage);
		    	coverage.setStatus(line.getStatusCd());
		    	coverage.setCoverageCd(coverageData.code);
		    	coverage.setDescription(coverageData.getCoverage());
		    	coverage.setId(risk.getId() + "-Coverage-" + coverage.getCoverageCd());
		    	
		    	coverage.setEarnedAmt(0D);
		    	coverage.setCommissionPct(0D);
		    	coverage.setContributionPct(0D);
		    	if(COVERAGE_CD_VET.equalsIgnoreCase(coverage.getCoverageCd())){
		    		// 12052014: Import change
		        	//coverage.setFullTermAmt(policy.getWrittenPremiumAmt());
		    		//coverage.setFinalPremiumAmt(policy.getWrittenPremiumAmt());
		    	}
		    	else{
		    		// 12052014: Import change
		        	//coverage.setFullTermAmt(0D);
		    		//coverage.setFinalPremiumAmt(0D);
		    	}
				
		    	DTOLimit limit = new DTOLimit();
		    	coverage.setDTOLimit(limit);
		    	limit.setLimitCd("Limit" + index);
		    	limit.setId(coverage.getId() + "-Limit-" + limit.getLimitCd());
		    	
		    	NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
		    	try {
					limit.setValue(String.valueOf(format.parse(coverageData.getCoverage_limit())));
				} catch (ParseException e) {
					e.printStackTrace();
				}
		    	
		    	DTODeductible deductible = new DTODeductible();
		    	coverage.setDTODeductible(deductible);
		    	deductible.setDeductibleCd("Deductible" + index);
		    	deductible.setValue(String.valueOf(policyData.getDeductible()));
		    	deductible.setId(coverage.getId() + "-Deductible-" + deductible.getDeductibleCd());
	    	}
	    	
	    	// Migrate missing coverages and mark as deleted.
	    	if(renewalPolicy){
	    		List<DTOCoverage> prevCoverages = prev_dtoApplication.getDTOLine().getDTORisk().getDTOCoverages();
	    		for (DTOCoverage prevCoverage : prevCoverages) {
	    			boolean matched = false;
	    			List<DTOCoverage> currCoverages = risk.getDTOCoverages();
	    			for (DTOCoverage currCoverage : currCoverages) {
						if(currCoverage.getCoverageCd().equals(prevCoverage.getCoverageCd())){
							matched = true;
							break;
						}
					}
	    			if(!matched){
	    				// Make a copy only if status is active.
	    				if(STATUS_DELETED.equalsIgnoreCase(prevCoverage.getStatus())){
	    					risk.getDTOCoverages().add(prevCoverage);
	    				}
	    				else{
		    				DTOCoverage copyCoverage = fromXml2DTOCoverage(toXml(prevCoverage));
		    				copyCoverage.setStatus(STATUS_DELETED);
		    				risk.getDTOCoverages().add(copyCoverage);
	    				}
	    				System.out.println("Found missing/deleted coverage:" + risk.getDTOCoverages().get(risk.getDTOCoverages().size()-1).getId());
	    			}
				}
	    	}
    	}
    	
    	DTOGLClass dtoGLClass = new DTOGLClass();
    	dtoGLClass.setId(risk.getId() + "-GLClass-1");
    	
    	risk.getDTOGLClasses().add(dtoGLClass);
    	Addr dtoGLClassAddr = new Addr();
    	dtoGLClass.setAddr(dtoGLClassAddr);
    	dtoGLClassAddr.setAddrTypeCd(ADD_TYPE_CODE_EXPOSURE_ADDR);
    	dtoGLClassAddr.setPostalCode(policyData.getPOSTCODE());
    	dtoGLClassAddr.setId(dtoGLClass.getId() + "-Addr-" + dtoGLClassAddr.getAddrTypeCd());
    	
    	/*
    	<DTOLine id="Line-1475906646-2126876472" 
    	      	StatusCd="Active" 
    	      	LineCd="Pet" 
    	      	FullTermAmt="893.33" 
    	      	RatingService="ManualRate" WrittenPremiumAmt="893.33" EarnedAmt="0.00" CommissionAmt="0.00" FinalPremiumAmt="893.33" TransactionCommissionAmt="89.33">
				<DTORisk id="Risk-1547560589-1676370506" 
					TypeCd="Pet" 
					FullTermAmt="893.33" FinalPremiumAmt="893.33" WrittenPremiumAmt="893.33" CommissionAmt="0.00" TransactionCommissionAmt="89.33" Status="Active" Description="Pet" PetType="Dog" Deductible="500" Breed="Maltese" CopayPct="0.7" InternetDiscInd="No" MedicalServicesDiscInd="No" PolicyHolderDiscountInd="No" VetStaffDiscInd="No" MicrochipDiscInd="No" BirthDt="20020428" BreedType="Pure" PetName="Kelly" WorkingDogDiscInd="No">
					<DTOCoverage id="Coverage-1061194909-254795884" 
						Status="Active" CoverageCd="VET" 
						FullTermAmt="893.33" WrittenPremiumAmt="893.33" FinalPremiumAmt="893.33" EarnedAmt="0.00" CommissionPct="10.00" ContributionPct="0.00" CommissionAmt="0.00" TransactionCommissionAmt="89.33" Description="Veterinary Expenses" CoinsurancePct="0">
						<DTOLimit id="Limit-272030270-20063427" LimitCd="Limit1" Value="14000"/>
						<DTODeductible id="Deductible-51085010-1739787917" DeductibleCd="Deductible1" Value="500"/>
				
				<DTOLine id="Line-1475906646-2126876472" StatusCd="Active" LineCd="Pet" RatingService="ManualRate">
					<DTORisk id="Risk-1547560589-1676370506" 
					TypeCd="Pet" 
					Status="Active" 
					Description="Pet" 
					PetType="Dog" 
					Deductible="500" 
					Breed="Maltese" 
					CopayPct="0.7" InternetDiscInd="No" MedicalServicesDiscInd="No" PolicyHolderDiscountInd="No" VetStaffDiscInd="No" 
					MicrochipDiscInd="No" BirthDt="20020428" BreedType="Pure" PetName="Kelly" WorkingDogDiscInd="No">
						<DTOCoverage id="Coverage-1061194909-254795884" 
						Status="Active" 
						CoverageCd="VET" 
						EarnedAmt="0.00" CommissionPct="10.00" ContributionPct="0.00" Description="Veterinary Expenses" CoinsurancePct="0">
							<DTOLimit id="Limit-272030270-20063427" LimitCd="Limit1" Value="14000"/>
							<DTODeductible id="Deductible-51085010-1739787917" DeductibleCd="Deductible1" Value="500"/>
						</DTOCoverage>
						<DTOGLClass id="GLClass-1921129349-1004335998">
							<Addr id="Addr-653652966-1566545157" AddrTypeCd="ExposureAddr" PostalCode="18974"/>
						</DTOGLClass>
    	 */
    }
    
    /**
     * @param seqNo
     * @param basicPolicy
     * @param amount
     * @param transactionDt
     * @param bookDt
     * @return
     */
    public static DTOAccount createItemAdjustment(int seqNo, DTOBasicPolicy basicPolicy, double amount, String transactionDt, String bookDt, String desc){
    	String policyDisplayNumber = basicPolicy.getPolicyDisplayNumber();
    	String policyNumber = basicPolicy.getPolicyNumber();
    	
    	DTOAccount account = new DTOAccount();
    	account.setAccountNumber(policyNumber);
    	account.setAccountDisplayNumber(policyDisplayNumber);
    	account.setId(basicPolicy.getId() + "-Account-" + account.getAccountDisplayNumber() + "-" + seqNo);
    	
    	DTOARTrans trans = new DTOARTrans();
    	account.getDTOARTrans().add(trans);
    	
    	trans.setTypeCd(TRANSACTION_TYPE_CODE_ITEM_ADJUSTMENT);
		trans.setAdjustmentCategoryCd("Premium");
		trans.setRequestedAmt(amount);
		trans.setAdjustmentTypeCd(getAdjustmentTypeCd(amount));
		if(amount < 0D){
			amount *= -1;
			trans.setRequestedAmt(amount);
		}
		
		trans.setDesc(desc);
    	trans.setPayBy(getPayByCode(null));
    	trans.setTransactionUserId(USER_CONVERSION);
    	trans.setId(account.getId() + "-ARTrans-" + trans.getTypeCd());
    	trans.setTransactionDt(transactionDt);
		trans.setBookDt(bookDt);
		
		return account;
    }
    
    /**
     * @param globalMap
     * @param app
     * @param accounts
     * @param includeWriteoff
     */
    public static void processItemAdjustment(Map<String, Object> globalMap, generated.DTOApplication app, List<DTOAccount> accounts, int includeWriteoff){
    	// Exit right away if no need to perform legacy difference for this policy.
    	if(includeWriteoff == 0){
    		return;
    	}
    	
    	Map<String, Double> spiPolicyPremiumByPolicyNumber = (Map<String, Double>) globalMap.get("spiPolicyPremiumByPolicyNumber");
    	
    	DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
    	String policyDisplayNumber = basicPolicy.getPolicyDisplayNumber();
    	
    	double amount = 0D;
    	Double spiPremium = spiPolicyPremiumByPolicyNumber.get(policyDisplayNumber);
    	if(spiPremium != null){
    		if(basicPolicy.getWrittenPremiumAmt() != null){
    			amount = new BigDecimal(spiPremium.doubleValue() - basicPolicy.getWrittenPremiumAmt().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    		}
    		if(amount == 0D){
    			return;
    		}
       	}
    	else{
    		amount = 0D;
    		System.err.println("URGENT: Failed to find SPI premium by policy:" + policyDisplayNumber);
    	}
    	
    	String transactionDt = basicPolicy.getEffectiveDt();
    	String bookDt = basicPolicy.getEffectiveDt();
    	if(accounts != null && !accounts.isEmpty()){
    		DTOAccount lastAccount = accounts.get(accounts.size()-1);
    		DTOARTrans lastTrans = lastAccount.getDTOARTrans().get(0);
    		if(lastTrans != null){
    			transactionDt = lastTrans.getTransactionDt();
    			bookDt = lastTrans.getBookDt();
    		}
    	}
    	
    	DTOAccount account = createItemAdjustment((accounts.size()), basicPolicy, amount, transactionDt, bookDt, "Diff w/ Legacy System");
    	
    	// Add account.
    	accounts.add(account);
    }
    
    /**
     * @param globalMap
     * @param app
     * @param accounts
     */
    public static void processInstallmentFee(Map<String, Object> globalMap, generated.DTOApplication app, routines.ServiceCharge charge, List<DTOAccount> accounts){
    	
    	DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
    	String policyDisplayNumber = basicPolicy.getPolicyDisplayNumber();
    	String policyNumber = basicPolicy.getPolicyNumber();
    	
    	DTOAccount account = new DTOAccount();
    	account.setAccountNumber(policyNumber);
    	account.setAccountDisplayNumber(policyDisplayNumber);
    	account.setId(basicPolicy.getId() + "-Account-" + account.getAccountDisplayNumber() + "-" + accounts.size());
    	
    	DTOARTrans trans = new DTOARTrans();
    	account.getDTOARTrans().add(trans);
    	
    	trans.setTypeCd(TRANSACTION_TYPE_CODE_ITEM_ADJUSTMENT);
		trans.setAdjustmentCategoryCd("InstallmentFee");
		trans.setAdjustmentTypeCd(WAIVE_CREDIT);
		
		// Fetch premium
		Object amountObj = charge.getAMOUNT();
    	if(amountObj != null){
    		double amount = new BigDecimal(amountObj.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    		trans.setRequestedAmt(amount);
    	}
    	else{
    		trans.setRequestedAmt(0D);
    		
    		System.out.println("URGENT: Failed to find Installment Fee by policy:" + policyDisplayNumber);
    	}
		trans.setDesc("Charge Installment Fee");
    	trans.setPayBy(getPayByCode(null));
    	trans.setTransactionUserId(USER_CONVERSION);
    	trans.setId(account.getId() + "-ARTrans-" + trans.getTypeCd());
    	
    	synchronized (DATE_FRMT) {
    		if(charge.getPAYMENTDATE() != null){
    			trans.setTransactionDt(DATE_FRMT.format(charge.getPAYMENTDATE()));
			}
		}
    	synchronized (DATE_FRMT) {
    		if(charge.getCREATEDDATE() != null){
    			trans.setBookDt(DATE_FRMT.format(charge.getCREATEDDATE()));
			}
		}
    	
    	// Add account.
    	accounts.add(0, account);
    	
    	/*
    	<DTOAccount id="ALZ5037878-Account-ALZ5037878-00" AccountDisplayNumber="ALZ5037878-00" AccountNumber="ALZ5037878">
			<DTOARTrans id="ALZ5037878-Account-ALZ5037878-00-ARTrans-ItemAdjustment2" 
			
			AdjustmentCategoryCd="InstallmentFee" 
			AdjustmentTypeCd="WaiveCredit" 
			BookDt="20130909" 
			Desc="Charge Installment Fee" 
			PayBy="Insured" 
			RequestedAmt="27.50" 
			TransactionDt="20130909" 
			TransactionUserId="Conversion" 
			TypeCd="ItemAdjustment"/>
		</DTOAccount>
		*/
    }
    
    /**
     * @param accts
     * @param existing
     * @param app
     * @param transData
     * @return
     */
    public static DTOAccount mapAccount(List<generated.DTOAccount> accts, DTOAccount existing, DTOApplication app, LegacyTransactionData transData){
    	DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
    	String policyNumber = basicPolicy.getPolicyNumber();

    	DTOAccount account = existing;
    	if(existing == null) {
    		account = new DTOAccount();
    		account.setAccountNumber(getPolicyNumber(transData.getACCOUNT_NUMBER()));
    		account.setAccountDisplayNumber(transData.getACCOUNT_NUMBER());
    	}
    	account.setId(basicPolicy.getId() + "-Account-" + account.getAccountDisplayNumber() + "-" + (accts.size()));
    	
    	DTOARTrans trans = new DTOARTrans();
    	account.getDTOARTrans().add(trans);
    	
    	double amount = 0D;
    	if(transData.getAMOUNT() != null){
    		amount = new BigDecimal(transData.getAMOUNT().toString()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    	}
    	
    	String transactionDt = basicPolicy.getEffectiveDt();
    	String bookDt = basicPolicy.getEffectiveDt();
    	synchronized (DATE_FRMT) {
    		if(transData.getPAYMENTDATE() != null){
    			transactionDt = DATE_FRMT.format(transData.getPAYMENTDATE());
			}
			if(transData.getORIGINALPAYMENTDATE() != null){
				bookDt = DATE_FRMT.format(transData.getORIGINALPAYMENTDATE());
			}
		}
    	
    	// Handle WOFF.
    	if(WAIVE_DEBIT.equalsIgnoreCase(getARReceiptTypeCd(transData))){
        	account = createItemAdjustment((accts.size()), basicPolicy, amount, transactionDt, bookDt,  (amount > 0D ? "Refund" : "Writeoff"));
        	accts.add(account);
        	return account;
    	}
    	
    	if(TRANSACTION_TYPE_CODE_RECEIPT.equalsIgnoreCase(transData.getTypeCd()) || CHECK.equalsIgnoreCase(getARReceiptTypeCd(transData))){
    		if (amount < 0D) {
    			amount = amount * -1;
    		}
    		
    		trans.setTypeCd(CHECK.equalsIgnoreCase(getARReceiptTypeCd(transData)) ? MANUAL_REFUND : TRANSACTION_TYPE_CODE_RECEIPT);
    		trans.setARReceiptAmt(amount);
    		trans.setCheckAmt(trans.getARReceiptAmt());
    		trans.setARReceiptTypeCd(getARReceiptTypeCd(transData));
    		trans.setARReceiptReference(transData.getREFERENCE());
    		
    		synchronized (DATE_FRMT) {
        		if(transData.getCREATEDDATE() != null){
    				trans.setARReceiptDt(DATE_FRMT.format(transData.getCREATEDDATE()));
    				trans.setBookDt(trans.getARReceiptDt());
    			}
    		}
    	}
    	else{
    		System.out.println("Failed to map transaction of type:" + transData.getTypeCd());
    		return null;
    	}
    	
    	trans.setDesc(CHECK.equalsIgnoreCase(getARReceiptTypeCd(transData)) ? "Refund" : transData.getREFERENCE());
    	trans.setPayBy(getPayByCode(transData));
    	trans.setTransactionUserId(USER_CONVERSION); // transData.getINSURED_PARTY_ID());
    	trans.setId(account.getId() + "-ARTrans-" + trans.getTypeCd());
    	
    	synchronized (DATE_FRMT) {
    		//try {
			//	Date effectiveDate = DATE_FRMT.parse(basicPolicy.getEffectiveDt());
			//	Date expirationDate = DATE_FRMT.parse(basicPolicy.getExpirationDt());
				if(transData.getPAYMENTDATE() != null){
			//		if(transData.getPAYMENTDATE().after(effectiveDate) && transData.getPAYMENTDATE().before(expirationDate)){
						trans.setTransactionDt(DATE_FRMT.format(transData.getPAYMENTDATE()));
			//		}
			//		else{
			//			trans.setTransactionDt(basicPolicy.getEffectiveDt());
			//		}
				}
			//} catch (ParseException e) {
			//	e.printStackTrace();
			//}
				
			if(transData.getORIGINALPAYMENTDATE() != null){
				trans.setBookDt(DATE_FRMT.format(transData.getORIGINALPAYMENTDATE()));
			}
		}
    	
    	/*
    	<DTOAccount AccountNumber="HO082772" AccountDisplayNumber="HO082772-18" >
        	<DTOARTrans TypeCd="Receipt" ARReceiptTypeCd="Check" ARReceiptReference="Conversion Receipt" 
        	ARReceiptDt="20090918" ARReceiptAmt="375.00" CheckAmt="375.00" Desc="Conversion Receipt" TransactionUserId="Conversion" 
        	TransactionDt="20090918" BookDt="20090918" PayBy="Insured" />
      	</DTOAccount>
    	 */
    	
    	if(CHECK.equalsIgnoreCase(getARReceiptTypeCd(transData))){
	    	/*
	    	<PartyInfo PartyTypeCd="PayToParty">
				<NameInfo NameTypeCd="PayToName" CommercialName="Dean, James"/>
				<Addr AddrTypeCd="PayToMailingAddr" Addr1="210 Dean AVE" City="San Jose" StateProvCd="CA" PostalCode="95132"/>               
			</PartyInfo>
	    	 */
    		DTOInsured dtoInsured = app.getDTOInsured();
        	
        	PartyInfo insuredPartyInfo = getPartyInfo(dtoInsured, PARTY_TYPE_CD_INSURED_PARTY);
        	if(insuredPartyInfo != null){
    			Addr insuredMailingAddr = routines.Utils.getPartyAddr(insuredPartyInfo, routines.Utils.ADDR_TYPE_CODE_INSURED_MAILING_ADDR);
    			
    			PartyInfo payToPartyPartyInfo = new PartyInfo();
        		trans.setPartyInfo(payToPartyPartyInfo);
        		payToPartyPartyInfo.setPartyTypeCd("PayToParty");
        		payToPartyPartyInfo.setId(trans.getId() + "-PartyInfo-" + payToPartyPartyInfo.getPartyTypeCd());
        		
        		NameInfo payToNameNameInfo = new NameInfo();
        		payToNameNameInfo.setNameTypeCd("PayToName");
        		payToNameNameInfo.setCommercialName(dtoInsured.getIndexName());
        		payToPartyPartyInfo.setNameInfo(payToNameNameInfo);
            	
        		if(insuredMailingAddr != null){
        			Addr newAddr = copyAddress(payToPartyPartyInfo, insuredMailingAddr);
            		newAddr.setAddrTypeCd("PayToMailingAddr");
            		newAddr.setVerificationHash(null);
            		payToPartyPartyInfo.getAddrs().add(newAddr);
        		}
        	}
    		
    	}
    	
    	if(account != null){
    		accts.add(account);
    		//System.out.println("policyNumber:" + policyNumber + ", transNumber=" + acctNew.getDTOARTrans().size());
    	}
    	return account;
    }
    
    /**
     * @param app
     * @param claimData
     */
    public static DTOClaim mapClaim(int transactionNumber, DTOApplication app, LegacyClaimData claimData){
    	/*
    	<DTOClaim id="DTOClaim-86938170-880943230" TypeCd="Claim" LossDt="20130916" ReportedDt="20131004" ReportedTm="14:22:04 EDT" ProductVersionIdRef="CL-alz-petplan-CA-Pet-v03-00-00" ProductLineCd="Pet" RiskIdRef="PolicyRisk-740649542-1281634382" CustomerRef="7848" Version="2.00">
    	 */
    	DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
    	DTOClaim dtoClaim = null;
    	if(claimData != null){
    		dtoClaim = new DTOClaim();
        	dtoClaim.setId(basicPolicy.getPolicyDisplayNumber() + "-Claim-" + claimData.getClaim_Number());
        	dtoClaim.setTransactionNumber(new BigInteger("1"));//new BigInteger(String.valueOf(transactionNumber)));
        	dtoClaim.setTypeCd(TRANSACTION_TYPE_CD); //CLAIM_TYPE_CD);
        	dtoClaim.setProductLineCd(LINE_CODE_PET);
        	dtoClaim.setCustomerRef(app.getCustomerRef());
    		synchronized (DATE_FRMT) {
        		if(claimData.getIncident_Date() != null){
        			dtoClaim.setLossDt(DATE_FRMT.format(claimData.getIncident_Date()));
        			synchronized (TIME_FRMT) {
        				dtoClaim.setLossTm(TIME_FRMT.format(claimData.getIncident_Date()));
        			}
    			}
    		}
        	synchronized (DATE_FRMT) {
        		if(claimData.getNotification_Date() != null){
        			dtoClaim.setReportedDt(DATE_FRMT.format(claimData.getNotification_Date()));
        			synchronized (TIME_FRMT) {
        				dtoClaim.setReportedTm(TIME_FRMT.format(claimData.getNotification_Date()));
        			}
    			}
    		}
        	dtoClaim.setProductVersionIdRef("CL-" + basicPolicy.getProductVersionIdRef());
        	dtoClaim.setRiskIdRef("None");//app.getDTOLine().getDTORisk().getId());
        	dtoClaim.setStatusCd(claimData.getOpen___Closed());
        	
        	dtoClaim.setClaimNumber(claimData.getClaim_Number());
        	//dtoClaim.setClaimType("Vet Fees - Accident");
        	dtoClaim.setLossCauseCd(claimData.getCondition());
        	
        	//dtoClaim.setContinuationInd("No");
        	dtoClaim.setShortDesc("NA");
        	dtoClaim.setSourceCd(PETPLAN_SOURCE_CD); // Petplan
			dtoClaim.setReportedBy(USER_CONVERSION);
			dtoClaim.setReportedTo(USER_CONVERSION);
			
        	DTOClaimPolicyInfo policyInfo = new DTOClaimPolicyInfo();
        	dtoClaim.setDTOClaimPolicyInfo(policyInfo);
        	policyInfo.setPolicyNumber(basicPolicy.getPolicyNumber());
        	
        	/*
        	<DTOClaimant id="Claimant-2039273911-237616057" ClaimantNumber="1" ClaimantTypeCd="First Party" ClaimantSubTypeCd="Insured" StatusCd="Open" StatusDt="20131004" PreferredDeliveryMethod="Email">
            <PartyInfo id="PartyInfo-1440970226-1213501163" PartyTypeCd="ClaimantParty">
              <PersonInfo id="PersonInfo-446600276-555492467" PersonTypeCd="ClaimantPerson" />
              <EmailInfo id="EmailInfo-1050479360-943541533" EmailTypeCd="ClaimantEmail" EmailAddr="peter.hose@iscs.com" PreferredInd="No" />
              <TaxInfo id="TaxInfo-649426749-1403476389" TaxTypeCd="ClaimantTax" />
              <PhoneInfo id="PhoneInfo-1083295683-584184915" PhoneTypeCd="ClaimantPhonePrimary" PhoneNumber="(999) 999-9999" PreferredInd="No" PhoneName="Mobile" />
              <NameInfo id="NameInfo-661850982-1911646661" IndexName="Doe, John" NameTypeCd="ClaimantName" GivenName="John" Surname="Doe" CommercialName="Doe, John" />
              <Addr id="Addr-542588660-1072043458" AddrTypeCd="ClaimantMailingAddr" Addr1="2836 Copley Ave" City="San Diego" StateProvCd="CA" PostalCode="92116-1413" />
              <PhoneInfo id="PhoneInfo-100632321-1257788858" PhoneTypeCd="ClaimantPhoneSecondary" PreferredInd="No" />
              <PhoneInfo id="PhoneInfo-1975798985-713907334" PhoneTypeCd="ClaimantFax" PreferredInd="No" />
            </PartyInfo>
            <DTOLitigation id="Litigation-16245749-311880274" />
            */
        	DTOClaimant dtoClaimant = new DTOClaimant();
        	dtoClaim.setDTOClaimant(dtoClaimant);
        	dtoClaimant.setClaimantNumber(new BigInteger("1"));
        	dtoClaimant.setId(dtoClaim.getId() + "-Claimant-" + dtoClaimant.getClaimantNumber().toString());
        	dtoClaimant.setClaimantTypeCd("First Party");
        	dtoClaimant.setClaimantSubTypeCd("Insured");
        	dtoClaimant.setStatusCd(claimData.getOpen___Closed());
        	dtoClaimant.setPreferredDeliveryMethod("Email");
        	
        	DTOInsured dtoInsured = app.getDTOInsured();
        	dtoClaimant.setIndexName(dtoInsured.getIndexName());
        	PartyInfo insuredPartyInfo = getPartyInfo(dtoInsured, PARTY_TYPE_CD_INSURED_PARTY);
        	
        	PartyInfo claimontPartyInfo = new PartyInfo();
        	dtoClaimant.setPartyInfo(claimontPartyInfo);
        	dtoClaimant.getPartyInfo().setPartyTypeCd(insuredPartyInfo.getPartyTypeCd().replaceFirst("Insured", "Claimant"));
        	dtoClaimant.getPartyInfo().setId(dtoClaimant.getId() + "-PartyInfo-" + dtoClaimant.getPartyInfo().getPartyTypeCd());
        	dtoClaimant.getPartyInfo().setPersonInfo(insuredPartyInfo.getPersonInfo());
        	dtoClaimant.getPartyInfo().getPersonInfo().setPersonTypeCd(dtoClaimant.getPartyInfo().getPersonInfo().getPersonTypeCd().replaceFirst("InsuredPersonal", "ClaimantPerson"));
        	
        	dtoClaimant.getPartyInfo().setNameInfo(insuredPartyInfo.getNameInfo());
        	dtoClaimant.getPartyInfo().getNameInfo().setNameTypeCd(dtoClaimant.getPartyInfo().getNameInfo().getNameTypeCd().replaceFirst("Insured", "Claimant"));
        	
        	dtoClaimant.getPartyInfo().setTaxInfo(insuredPartyInfo.getTaxInfo());
        	dtoClaimant.getPartyInfo().getTaxInfo().setTaxTypeCd(dtoClaimant.getPartyInfo().getTaxInfo().getTaxTypeCd().replaceFirst("Insured", "Claimant"));
        	
        	for(Addr addr : insuredPartyInfo.getAddrs()){
        		Addr newAddr = copyAddress(claimontPartyInfo, addr);
        		newAddr.setAddrTypeCd(addr.getAddrTypeCd().replaceFirst("Insured", "Claimant"));
        		System.err.println("Adding address:" + addr.getAddrTypeCd());
        		dtoClaimant.getPartyInfo().getAddrs().add(newAddr);
        	}
        	Iterator<Addr> addrIterator = dtoClaimant.getPartyInfo().getAddrs().iterator();
        	while (addrIterator.hasNext()) {
				Addr addr = (Addr) addrIterator.next();
				if(!"ClaimantMailingAddr".equalsIgnoreCase(addr.getAddrTypeCd())){
					System.err.println("Removing address:" + addr.getAddrTypeCd());
					addrIterator.remove();
				}
			}
        	
        	for(EmailInfo email : insuredPartyInfo.getEmailInfo()){
        		EmailInfo newEmail = copyEmail(claimontPartyInfo, email);
        		newEmail.setEmailTypeCd(email.getEmailTypeCd().replaceFirst("Insured", "Claimant"));
        		dtoClaimant.getPartyInfo().getEmailInfo().add(newEmail);
        	}
        	for(PhoneInfo phone : insuredPartyInfo.getPhoneInfos()){
        		PhoneInfo newPhone = copyPhone(claimontPartyInfo, phone);
        		newPhone.setPhoneTypeCd(phone.getPhoneTypeCd().replaceFirst("Insured", "Claimant"));
        		dtoClaimant.getPartyInfo().getPhoneInfos().add(newPhone);
        	}
        	
        	DTOOtherInsurance dtoOtherInsurance = new DTOOtherInsurance();
        	dtoClaimant.setDTOOtherInsurance(dtoOtherInsurance);
        	dtoOtherInsurance.setId(dtoClaimant.getId() + "-OtherInsurance-" + 1);
        	PartyInfo otherInsPartyInfo = new PartyInfo();
        	dtoOtherInsurance.setPartyInfo(otherInsPartyInfo);
        	otherInsPartyInfo.setPartyTypeCd("OtherInsuranceParty");
        	
        	PersonInfo personInfo = new PersonInfo();
        	personInfo.setPersonTypeCd("OtherInsurancePerson");
        	personInfo.setId(otherInsPartyInfo.getId() + "-PersonInfo-" + personInfo.getPersonTypeCd());
        	otherInsPartyInfo.setPersonInfo(personInfo);
        	
        	EmailInfo emailInfo = new EmailInfo();
        	emailInfo.setEmailTypeCd("OtherInsuranceEmail");
        	emailInfo.setPreferredInd(INDICATOR_NO);
        	otherInsPartyInfo.getEmailInfo().add(emailInfo);
        	
        	NameInfo nameInfo = new NameInfo();
        	nameInfo.setNameTypeCd("OtherInsuranceName");
        	nameInfo.setId(otherInsPartyInfo.getId() + "-NameInfo-" + nameInfo.getNameTypeCd());
        	otherInsPartyInfo.setNameInfo(nameInfo);
        	
        	Addr addr = new Addr();
        	addr.setAddrTypeCd("OtherInsuranceMailingAddr");
        	addr.setId(otherInsPartyInfo.getId() + "-Addr-" + addr.getAddrTypeCd());
        	otherInsPartyInfo.getAddrs().add(addr);
        	
        	PhoneInfo phoneInfo = new PhoneInfo();
        	phoneInfo.setPhoneTypeCd("OtherInsurancePhonePrimary");
		    phoneInfo.setId(otherInsPartyInfo.getId() + "-Phone-" + phoneInfo.getPhoneTypeCd());
	    	phoneInfo.setPreferredInd(INDICATOR_NO);
		    otherInsPartyInfo.getPhoneInfos().add(phoneInfo);
	    	
		    phoneInfo = new PhoneInfo();
        	phoneInfo.setPhoneTypeCd("OtherInsurancePhoneSecondary");
		    phoneInfo.setId(otherInsPartyInfo.getId() + "-Phone-" + phoneInfo.getPhoneTypeCd());
	    	phoneInfo.setPreferredInd(INDICATOR_NO);
		    otherInsPartyInfo.getPhoneInfos().add(phoneInfo);
		    
		    phoneInfo = new PhoneInfo();
        	phoneInfo.setPhoneTypeCd("OtherInsuranceFax");
		    phoneInfo.setId(otherInsPartyInfo.getId() + "-Phone-" + phoneInfo.getPhoneTypeCd());
	    	phoneInfo.setPreferredInd(INDICATOR_NO);
		    otherInsPartyInfo.getPhoneInfos().add(phoneInfo);
		    
		    // Other Party Info.
		    
	        
	    
        	/*
        	 <DTOOtherInsurance id="OtherInsurance-267291275-1976153048">
		        <PartyInfo id="PartyInfo-222193234-2075105352" PartyTypeCd="OtherInsuranceParty">
		          <PersonInfo id="PersonInfo-1555308545-899764531" PersonTypeCd="OtherInsurancePerson" />
		          <EmailInfo id="EmailInfo-1910113990-552790927" EmailTypeCd="OtherInsuranceEmail" PreferredInd="No" />
		          <PhoneInfo id="PhoneInfo-742853167-1810737758" PhoneTypeCd="OtherInsurancePhonePrimary" PreferredInd="No" />
		          <NameInfo id="NameInfo-1161223784-356753752" NameTypeCd="OtherInsuranceName" />
		          <Addr id="Addr-185967440-966057695" AddrTypeCd="OtherInsuranceMailingAddr" />
		          <PhoneInfo id="PhoneInfo-1078737069-1276970709" PhoneTypeCd="OtherInsurancePhoneSecondary" PreferredInd="No" />
		          <PhoneInfo id="PhoneInfo-95138045-1257288947" PhoneTypeCd="OtherInsuranceFax" PreferredInd="No" />
		        </PartyInfo>
		      </DTOOtherInsurance>
        	 */
        	
        	DTOLitigation dtoLitigation = new DTOLitigation();
        	dtoLitigation.setId(dtoClaimant.getId() + "-Litigation-" + 1);
        	dtoClaimant.setDTOLitigation(dtoLitigation);
        	
        	/*
        	<DTOAssignedProvider id="AssignedProvider-1911666438-874980699" AssignedProviderTypeCd="AssignedAdjuster" ProviderRef="36583" ProviderTypeCd="Adjuster" />
		    <DTOPropertyDamaged id="PropertyDamaged-740027529-1564880126" PropertyTypeCd="Property">
		        <Addr id="Addr-1923575863-1320707503" AddrTypeCd="LossLocationAddr" Addr1="2836 Copley Ave" City="San Diego" StateProvCd="CA" PostalCode="92116-1413" County="San Diego" RegionCd="United States" VerificationHash="2836 COPLEY AVE SAN DIEGO SAN DIEGO CA 921161413 UNITED STATES" Latitude="32.765791" Longitude="-117.133301" DPV="1" DPVDesc="Yes, the input record is a valid mailing address" DPVNotes="1, 3" DPVNotesDesc="The input address matched the ZIP+4 record, The input address matched the DPV record" />
		    </DTOPropertyDamaged>
        	 */
        	
        	DTOAssignedProvider dtoAssignedProvider = new DTOAssignedProvider();
        	dtoClaimant.getDTOAssignedProviders().add(dtoAssignedProvider);
        	dtoAssignedProvider.setId(dtoClaimant.getId() + "-AssignedProvider-" + 1);
        	dtoAssignedProvider.setAssignedProviderTypeCd("AssignedAdjuster");
        	dtoAssignedProvider.setProviderRef(new BigInteger("1909"));
        	dtoAssignedProvider.setProviderTypeCd("Adjuster");
        	
        	dtoAssignedProvider = new DTOAssignedProvider();
        	dtoClaimant.getDTOAssignedProviders().add(dtoAssignedProvider);
        	dtoAssignedProvider.setAssignedProviderTypeCd("AssignedOther1");
        	dtoAssignedProvider.setId(dtoClaimant.getId() + "-AssignedProvider-" + dtoAssignedProvider.getAssignedProviderTypeCd());
        	dtoAssignedProvider.setProviderRef(new BigInteger("1909"));
        	dtoAssignedProvider.setProviderTypeCd("Adjuster");
        	
        	dtoAssignedProvider = new DTOAssignedProvider();
        	dtoClaimant.getDTOAssignedProviders().add(dtoAssignedProvider);
        	dtoAssignedProvider.setAssignedProviderTypeCd("AssignedOther2");
        	dtoAssignedProvider.setId(dtoClaimant.getId() + "-AssignedProvider-" + dtoAssignedProvider.getAssignedProviderTypeCd());
        	dtoAssignedProvider.setProviderRef(new BigInteger("1909"));
        	dtoAssignedProvider.setProviderTypeCd("Adjuster");
        	
        	dtoAssignedProvider = new DTOAssignedProvider();
        	dtoClaimant.getDTOAssignedProviders().add(dtoAssignedProvider);
        	dtoAssignedProvider.setAssignedProviderTypeCd("AssignedOther3");
        	dtoAssignedProvider.setId(dtoClaimant.getId() + "-AssignedProvider-" + dtoAssignedProvider.getAssignedProviderTypeCd());
        	dtoAssignedProvider.setProviderRef(new BigInteger("1909"));
        	dtoAssignedProvider.setProviderTypeCd("Adjuster");
        	
        	dtoAssignedProvider = new DTOAssignedProvider();
        	dtoClaimant.getDTOAssignedProviders().add(dtoAssignedProvider);
        	dtoAssignedProvider.setAssignedProviderTypeCd("AssignedOther4");
        	dtoAssignedProvider.setId(dtoClaimant.getId() + "-AssignedProvider-" + dtoAssignedProvider.getAssignedProviderTypeCd());
        	dtoAssignedProvider.setProviderRef(new BigInteger("1909"));
        	dtoAssignedProvider.setProviderTypeCd("Adjuster");
        	
        	DTOPropertyDamaged dtoPropertyDamaged = new DTOPropertyDamaged();
        	dtoClaimant.setDTOPropertyDamaged(dtoPropertyDamaged);
        	dtoPropertyDamaged.setId(dtoClaimant.getId() + "-PropertyDamaged-" + 1);
        	dtoPropertyDamaged.setPropertyTypeCd("Property");
        	if(!dtoClaimant.getPartyInfo().getAddrs().isEmpty()){
        		
        		Addr newAddr = copyAddress(claimontPartyInfo, dtoClaimant.getPartyInfo().getAddrs().get(0));
        		newAddr.setAddrTypeCd("LossLocationAddr");
        		
        		dtoClaim.setLossLocationDesc(newAddr.getAddr1());
        		dtoClaim.setAddr(newAddr);
        		dtoClaim.getAddr().setId(dtoClaim.getId() + dtoClaim.getAddr().getAddrTypeCd());
        		
        		newAddr = copyAddress(claimontPartyInfo, dtoClaimant.getPartyInfo().getAddrs().get(0));
        		newAddr.setAddrTypeCd("LossLocationAddr");
        		
        		dtoPropertyDamaged.setAddr(newAddr);
        		dtoPropertyDamaged.setLocationDesc(newAddr.getAddr1());
	        	dtoPropertyDamaged.getAddr().setId(dtoClaimant.getId() + dtoPropertyDamaged.getAddr().getAddrTypeCd());
        	}
        	
        	DTOClaimantTransaction claimant_transaction = new  DTOClaimantTransaction();
			dtoClaimant.setDTOClaimantTransaction(claimant_transaction);
			claimant_transaction.setTransactionCd("Fast Track");
			claimant_transaction.setStatusCd("Closed");
			claimant_transaction.setPaymentTypeCd("Indemnity");
			claimant_transaction.setPaymentMethodCd("Check - Manual");
			claimant_transaction.setPaymentAccountCd("ALZClaims");
			claimant_transaction.setPayToName(claimData.getFirst_Name() + " " + claimData.getLast_Name()); 
			claimant_transaction.setPayToClaimantInd(INDICATOR_YES); 
			claimant_transaction.setMemo("For Pet Hurt"); 
			claimant_transaction.setMemoCd("First and Final Payment"); 
			if(claimData.getPaid_Amount() == null){
				claimant_transaction.setPaidAmt(0.00);
			}
			else{
				BigDecimal paidAmount = new BigDecimal(claimData.getPaid_Amount().toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
				if(paidAmount.doubleValue() < 0D){
					paidAmount = paidAmount.negate();
				}
				claimant_transaction.setPaidAmt(paidAmount.doubleValue()); 
			}
			claimant_transaction.setClassificationCd("Indemnity"); 
			claimant_transaction.setSequenceNumber(new BigInteger("1")); 
			claimant_transaction.setTransactionNumber(new BigInteger("1"));
			synchronized (DATE_FRMT) {
        		if(claimData.getCLOSUREDATE() != null){
        			claimant_transaction.setRecoveryCheckDt(DATE_FRMT.format(claimData.getCLOSUREDATE()));
    			}
        		else{
        			claimant_transaction.setRecoveryCheckDt(DATE_FRMT.format(new Date()));
        		}
				claimant_transaction.setBookDt(DATE_FRMT.format(new Date()));
				claimant_transaction.setTransactionDt(DATE_FRMT.format(new Date()));
    		}
			claimant_transaction.setRecoveryCheckNumber(claimData.getClaim_Number());
			claimant_transaction.setAutomatedPaymentInd("false"); 
			claimant_transaction.setVoidAllowedInd("true"); 
			claimant_transaction.setId(dtoClaimant.getId() + "-ClaimantTransaction-" + claimant_transaction.getTransactionCd());
			claimant_transaction.setPartyInfo(dtoClaimant.getPartyInfo());
			
			PartyInfo mailingParty = new PartyInfo();
	    	claimant_transaction.setPartyInfo(mailingParty);
	    	mailingParty.setPartyTypeCd("MailingParty");
	    	mailingParty.setId(claimant_transaction.getId() + "-PartyInfo-" + mailingParty.getPartyTypeCd());
	    	
	    	mailingParty.setPersonInfo(dtoClaimant.getPartyInfo().getPersonInfo());
	    	mailingParty.setNameInfo(dtoClaimant.getPartyInfo().getNameInfo());
	    	
	    	TaxInfo mailingTaxInfo = new TaxInfo();
	    	mailingTaxInfo.setTaxIdTypeCd("SSN");
	    	mailingTaxInfo.setSSN("999-99-9999");
	    	mailingTaxInfo.setTaxTypeCd("MailingTaxInfo");
	    	mailingTaxInfo.setId(mailingParty.getId() + "-TaxInfo-" + mailingTaxInfo.getTaxTypeCd());
	    	mailingParty.setTaxInfo(mailingTaxInfo);
	    	
	    	mailingParty.getAddrs().addAll(dtoClaimant.getPartyInfo().getAddrs());
	    	mailingParty.getEmailInfo().addAll(dtoClaimant.getPartyInfo().getEmailInfo());
	    	mailingParty.getPhoneInfos().addAll(dtoClaimant.getPartyInfo().getPhoneInfos());
	    	
			DTOFeatureAllocation feature_allocation = new DTOFeatureAllocation();
			claimant_transaction.setDTOFeatureAllocation(feature_allocation);
			feature_allocation.setFeatureCd("VET");
			feature_allocation.setPaidAmt(claimant_transaction.getPaidAmt());
			feature_allocation.setStatusCd(claimData.getOpen___Closed());
			feature_allocation.setId(claimant_transaction.getId() + "-FeatureAllocation-" + feature_allocation.getFeatureCd());
			
			DTOReserveAllocation reserve_allocation = new DTOReserveAllocation();
			feature_allocation.setDTOReserveAllocation(reserve_allocation);
			reserve_allocation.setReserveCd("Indemnity");
			reserve_allocation.setPaidAmt(claimant_transaction.getPaidAmt());
			reserve_allocation.setStatusCd(claimData.getOpen___Closed());
			reserve_allocation.setId(claimant_transaction.getId() + "-ReserveAllocation-" + reserve_allocation.getReserveCd());
			
			ElectronicPaymentDestination electronic_payment_destination = new ElectronicPaymentDestination();
			electronic_payment_destination.setDestinationTypeCd("ClaimantDestination");
			claimant_transaction.setElectronicPaymentDestination(electronic_payment_destination);
			
			/*
			DTOFeature feature = new  DTOFeature();
			dtoClaimant.setDTOFeature(feature);
			feature.setFeatureCd("VET"); 
			feature.setDescription("Veterinary Expenses"); 
			feature.setStatusCd(claimData.getOpen___Closed());
			feature.setPaidAmt(reserve_allocation.getPaidAmt()); 
			feature.setReserveAmt(new BigDecimal(claimData.getReserve_Amount().toString()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());	//("0.00"); 
			feature.setRecoveryAmt(0.00); 
			feature.setExpectedRecoveryAmt(0.00);
			
			DTOReserve reserve = new DTOReserve();
			feature.setDTOReserve(reserve);
			reserve.setReserveCd("Indemnity"); 
			reserve.setDescription("Indemnity"); 
			reserve.setStatusCd(claimData.getOpen___Closed());
			reserve.setPaidAmt(feature.getPaidAmt()); 
			*/
			
			DTOClaimTransactionInfo claim_transaction_info = new DTOClaimTransactionInfo();
			dtoClaim.setDTOClaimTransactionInfo(claim_transaction_info);
			claim_transaction_info.setTransactionCd(1 == dtoClaim.getTransactionNumber().intValue() ? "New Claim" : TRANSACTION_TYPE_CD); 
			claim_transaction_info.setTransactionNumber(dtoClaim.getTransactionNumber());
			claim_transaction_info.setBookDt(claimant_transaction.getBookDt());
			claim_transaction_info.setTransactionDt(claimant_transaction.getBookDt());
			synchronized (TIME_FRMT) {
				claim_transaction_info.setTransactionTm(TIME_FRMT.format(new Date()));
			}
			claim_transaction_info.setSourceCd("Petplan");
			claim_transaction_info.setTransactionUser(USER_CONVERSION);
			
			DTOClaimTransactionHistory claim_transaction_history = new DTOClaimTransactionHistory();
			dtoClaim.setDTOClaimTransactionHistory(claim_transaction_history);
    	}
    	return dtoClaim;
    }
    
    /**
     * @param app
     * @param endorsements
     * @param coverageBySchema
     * @param policyData
     */
    public static void mapAppEndorsement(DTOApplication app, LegacyEndosement legacyEndosement, int transactionNumber, Map<Integer, List<routines.LegacyCoverage>> coverageBySchema, LegacyPolicyData policyData){
    	/*
    	<TransactionHistory id="TransactionHistory-785831361-443255277" TransactionCd="Endorsement" TransactionEffectiveDt="20140924" TransactionEffectiveTm="12:01am" 
    		TransactionUser="jbenson" TransactionDt="20140912" TransactionTm="10:54:37 EDT" TransactionShortDescription="Endorsement" TransactionNumber="3" 
    		InforcePremiumAmt="153.92" WrittenPremiumAmt="-14.93" ChangeInfoRef="ChangeInfo-1782286434-1786187882" BookDt="20140913" MasterTransactionNumber="3" 
    		OutputTypeCd="Full" ApplicationRef="428116" SourceCd="Innovation" DelayInvoiceInd="false" WrittenFeeAmt="0.00" WrittenCommissionFeeAmt="0.00" 
    		TransactionCommissionAmt="0.00">
			<TransactionText id="TransactionText-652894992-1712178021" TransactionTextTypeCd="Declaration" Text="Breed code changed from Border Collie Mix to Dachshund  Mix"/>
		</TransactionHistory>
		*/
    	app.setTypeCd(TRANSACTION_CD_ENDORSEMENT);
    	app.setDescription(TRANSACTION_CD_ENDORSEMENT);
    	DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
    	basicPolicy.setTransactionCd(TRANSACTION_CD_ENDORSEMENT);
    	basicPolicy.setRenewedFromPolicyNumber(null);
    	basicPolicy.setRewriteFromPolicyNumber(null);
    	
    	// Increment transaction number.
    	basicPolicy.setTransactionNumber(new BigInteger(String.valueOf(basicPolicy.getTransactionNumber().intValue() + 1)));
    	
    	// Increment policy version.
    	// Changed on 03052015
    	//setPolicyVersion(app, policyData, null);
    	
		DTOTransactionHistory transHistory = new DTOTransactionHistory();
		transHistory.setTransactionNumber(new BigInteger(String.valueOf(transactionNumber)));
		transHistory.setId(basicPolicy.getId() + "-TransactionHistory-" + (transactionNumber));
		transHistory.setTransactionCd(TRANSACTION_CD_ENDORSEMENT);
		transHistory.setTransactionShortDescription(TRANSACTION_CD_ENDORSEMENT);
		transHistory.setSourceCd(PETPLAN_SOURCE_CD);
		transHistory.setTransactionUser(USER_CONVERSION);
		transHistory.setInforcePremiumAmt(basicPolicy.getWrittenPremiumAmt());
		
		if(legacyEndosement.getPREMIUM() != null){
			// 12052014: Import change
	    	//basicPolicy.setFullTermAmt(legacyEndosement.getPREMIUM().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			//basicPolicy.setWrittenPremiumAmt(legacyEndosement.getPREMIUM().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			basicPolicy.setConversionWrittenPremiumAmt(legacyEndosement.getPREMIUM().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		if(legacyEndosement.getWRITTEN_PREMIUM() != null){
			// 12052014: Import change
	    	//basicPolicy.setWrittenPremiumAmt(legacyEndosement.getWRITTEN_PREMIUM().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		if(legacyEndosement.getFINAL_PREMIUM() != null){
			// 12052014: Import change
	    	//basicPolicy.setFinalPremiumAmt(legacyEndosement.getFINAL_PREMIUM().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		
		synchronized (DATE_FRMT) {
			if(legacyEndosement.getPOLICYSTARTDATE() != null){
    			basicPolicy.setEffectiveDt(DATE_FRMT.format(legacyEndosement.getPOLICYSTARTDATE()));
			}
			if(legacyEndosement.getENDORSEMENTDATE() != null){
				transHistory.setTransactionEffectiveDt(DATE_FRMT.format(legacyEndosement.getENDORSEMENTDATE()));
				//System.out.println("Setting <DTOTransactionHistory TransactionEffectiveDt='" + transHistory.getTransactionEffectiveDt() + "'");
			}
			if(legacyEndosement.getTRANSACTION_DATE() != null){
				transHistory.setTransactionDt(DATE_FRMT.format(legacyEndosement.getTRANSACTION_DATE()));
				//System.out.println("Setting <DTOTransactionHistory TransactionDt='" + transHistory.getTransactionDt() + "'");
			}
		}
		
		// Set Policy Form and campaign.
		basicPolicy.setPolicyForm(legacyEndosement.SCHEMENAME != null ? legacyEndosement.SCHEMENAME : POLICY_FORM_PLATINUM);
    	if(legacyEndosement.CAMPAIGN_ID != null && !legacyEndosement.CAMPAIGN_ID.trim().equals("") && !legacyEndosement.CAMPAIGN_ID.trim().equals("0")){
    		basicPolicy.setCampaignCd(legacyEndosement.CAMPAIGN_ID.trim());
    	}
		
		// Set transaction info.
    	DTOTransactionInfo transInfo = app.getDTOTransactionInfo();
    	transInfo.setTransactionCd(TRANSACTION_CD_ENDORSEMENT);
    	transInfo.setTransactionShortDescription(TRANSACTION_CD_ENDORSEMENT);
    	transInfo.setTransactionEffectiveDt(transHistory.getTransactionEffectiveDt());
    	if(legacyEndosement.getTRANSACTION_DATE() != null){
    		transInfo.setBookDt(transHistory.getTransactionDt());
			//System.out.println("Setting <DTOTransactionInfo BookDt='" + transInfo.getBookDt() + "'");
		}
    	
    	// Add transaction text to history.
		DTOTransactionText transText = new DTOTransactionText();
		transHistory.setDTOTransactionText(transText);
		transText.setId(transHistory.getId() + "-TransactionText-1");
		transText.setTransactionTextTypeCd("Declaration");
		transText.setText(TRANSACTION_CD_ENDORSEMENT);
		
		basicPolicy.getDTOTransactionHistory().add(transHistory);
		
		// Reset endorsement data. This is the opposite of applyEnsorsementData.
		resetEnsorsementData(app, coverageBySchema, policyData);
    }
    
    /**
     * @param app
     * @param coverageBySchema
     * @param policyData
     */
    public static void resetEnsorsementData(DTOApplication app, Map<Integer, List<routines.LegacyCoverage>> coverageBySchema, LegacyPolicyData policyData){
    	DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
    	List<DTOCoverage> originalCoverages = app.getDTOLine().getDTORisk().getDTOCoverages();
    	DTOLine line = app.getDTOLine();
    	DTORisk risk = line.getDTORisk();
    	
    	line.setStatusCd(ACTIVE);
    	line.setLineCd(LINE_CODE_PET);
    	line.setRatingService(RATING_SERVICE_MANUAL_RATE);
    	line.setId(basicPolicy.getId() + "-Line-" + line.getLineCd());
    	
    	risk.setStatus(line.getStatusCd());
    	risk.setTypeCd(line.getLineCd());
    	risk.setDescription(risk.getTypeCd());
    	risk.setPetType(policyData.getPetType());
    	risk.setPetName(policyData.getPETNAME());
    	risk.setBreed(policyData.getPETBREED_ID()); // policyData.getPETBREED_ID()); // policyData.getPETPLAN_PET_BREED_DEBUG());
    	risk.setBreedType(policyData.getBreedType());
    	synchronized (DATE_FRMT) {
    		if(policyData.getPETDOB() != null){
    			risk.setBirthDt(DATE_FRMT.format(policyData.getPETDOB()));
			}
		}
    	if(policyData.getDeductible() != null){
    		risk.setDeductible(policyData.getDeductible().toString());
    	}
    	else{
    		risk.setDeductible(Integer.toString(0));
    	}
    	
    	if(policyData.getCopay() != null){
    		String copayPct = policyData.getCopay().toString();
    		if("1.0".equals(copayPct)){
    			copayPct = "1";
    		}
    		risk.setCopayPct(copayPct);
    		//risk.setCopayPct(String.valueOf(policyData.getCopay().setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue()));
    	}
    	else{
    		risk.setCopayPct(Double.toString(0D));
    	}
    	
    	risk.setVetStaffDiscInd(policyData.getVETSTAFF() ? INDICATOR_YES : INDICATOR_NO);
    	risk.setMicrochipDiscInd(policyData.getMICROCHIPPED() ? INDICATOR_YES : INDICATOR_NO);
    	risk.setMedicalServicesDiscInd(policyData.getPETMEDSERV() ? INDICATOR_YES : INDICATOR_NO);
    	risk.setWorkingDogDiscInd(policyData.getWORKINGDOG() == null ? INDICATOR_NO : (policyData.getWORKINGDOG() ? INDICATOR_YES : INDICATOR_NO));
    	risk.setInternetDiscInd(policyData.getWEB_DISCOUNT() == 1 ? INDICATOR_YES : INDICATOR_NO);
    	risk.setPolicyHolderDiscountInd(policyData.getPOLICYHOLDER_DISCOUNT() ? INDICATOR_YES : INDICATOR_NO);
    	risk.setId(line.getId() + "-Risk-" + risk.getTypeCd());
    	
    	//risk.setFullTermAmt(basicPolicy.getFullTermAmt());
    	//risk.setWrittenPremiumAmt(basicPolicy.setWrittenPremiumAmt(value));
    	//risk.setFinalPremiumAmt(basicPolicy.getFinalPremiumAmt());
    	
    	for (DTOCoverage dtoCoverage : originalCoverages) {
    		if(policyData.getDeductible() != null){
		    	DTODeductible deductible = dtoCoverage.getDTODeductible();
		    	deductible.setValue(String.valueOf(policyData.getDeductible()));
			}
		}
    }
    
    /**
     * @param app
     * @param legacyEndosement
     * @param coverageBySchema
     * @param policyData
     */
    public static void applyEnsorsementData(DTOApplication app, LegacyEndosement legacyEndosement, Map<Integer, List<routines.LegacyCoverage>> coverageBySchema, LegacyPolicyData policyData){
    	DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
    	if(legacyEndosement.getFINAL_PREMIUM() != null){
			// 03052014: Import change
			basicPolicy.setConversionWrittenPremiumAmt(legacyEndosement.getFINAL_PREMIUM().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
    	
    	List<DTOCoverage> originalCoverages = app.getDTOLine().getDTORisk().getDTOCoverages();
    	DTOLine line = app.getDTOLine();
    	DTORisk risk = line.getDTORisk();
    	
    	// Set endorsement values.
    	risk.setPetName(legacyEndosement.getPETNAME());
    	risk.setBreed(legacyEndosement.getPETBREED_ID());
    	synchronized (DATE_FRMT) {
    		if(legacyEndosement.getPETDOB() != null){
    			risk.setBirthDt(DATE_FRMT.format(legacyEndosement.getPETDOB()));
			}
		}
    	if(legacyEndosement.getDeductible() != null){
    		risk.setDeductible(legacyEndosement.getDeductible().toString());
    	}
    	else{
    		risk.setDeductible(Integer.toString(0));
    	}
    	if(legacyEndosement.getCopay() != null){
    		String copayPct = legacyEndosement.getCopay().toString();
    		if("1.0".equals(copayPct)){
    			copayPct = "1";
    		}
    		risk.setCopayPct(copayPct);
    	}
    	else{
    		risk.setCopayPct(Double.toString(0D));
    	}
    	//legacyEndosement.getSCHEMETABLE_ID()
    	risk.setVetStaffDiscInd(legacyEndosement.getVETSTAFF() != null && legacyEndosement.getVETSTAFF() == 1 ? INDICATOR_YES : INDICATOR_NO);
    	risk.setMicrochipDiscInd(legacyEndosement.getMICROCHIPPED() != null && legacyEndosement.getMICROCHIPPED() == 1 ? INDICATOR_YES : INDICATOR_NO);
    	risk.setMedicalServicesDiscInd(legacyEndosement.getPETMEDSERV() != null && legacyEndosement.getPETMEDSERV() == 1 ? INDICATOR_YES : INDICATOR_NO);
    	risk.setWorkingDogDiscInd(legacyEndosement.getWORKINGDOG() == null ? INDICATOR_NO : (legacyEndosement.getWORKINGDOG() == 1 ? INDICATOR_YES : INDICATOR_NO));
    	risk.setInternetDiscInd(legacyEndosement.getWEB_DISCOUNT() != null && legacyEndosement.getWEB_DISCOUNT() == 1 ? INDICATOR_YES : INDICATOR_NO);
    	risk.setPolicyHolderDiscountInd(legacyEndosement.getPOLICYHOLDER_DISCOUNT() != null && legacyEndosement.getPOLICYHOLDER_DISCOUNT() == 1 ? INDICATOR_YES : INDICATOR_NO);
	
    	risk.setId(line.getId() + "-Risk-" + risk.getTypeCd());
    	
    	// NOTE: Perform coverage mapping. 
    	// Endorsements will never have schema changes.
    	for (DTOCoverage dtoCoverage : originalCoverages) {
    		if(legacyEndosement.getDeductible() != null){
		    	DTODeductible deductible = dtoCoverage.getDTODeductible();
		    	deductible.setValue(String.valueOf(legacyEndosement.getDeductible()));
			}
		}
    }
    
    /**
	 * @param dtoApplication
	 * @param legacyPolicyNote
	 */
	public static void applyPolicyNote(DTOApplication app, List<LegacyPolicyNote> legacyPolicyNotes) {
		DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
		
		/*
		<Note id="Note-353423737-52980157" TemplateId="PolicyNote0003" Description="Alex Desc" PriorityCd="3" Memo="Note text..." Status="Active" AddDt="20141010" AddTm="10:14:05 EDT" AddUser="admin">
			<LinkReference id="LinkReference-1895268133-146575380" IdRef="BasicPolicy-ALZ3031691" ModelName="Policy" Status="Active" />
			<Tag id="Tag-1013768187-266663611" Name="Correspondence" TagTemplateIdRef="Correspondence" />
		</Note>

		TemplateId
		Description
		PriorityCd
		Memo
		Status
		AddDt
		AddTm
		AddUser
		StickyInd

		*/
		
		int index = 1;
		for (LegacyPolicyNote legacyPolicyNote : legacyPolicyNotes) {
			Note note = new Note();
			note.setId(basicPolicy.getId() + "-Note-" + index++);
			note.setTemplateId(legacyPolicyNote.getTemplateId());
			note.setDescription(legacyPolicyNote.getDescription());
			note.setPriorityCd(legacyPolicyNote.getPriorityCd());
			note.setMemo(legacyPolicyNote.getMemo());
			note.setStatus(legacyPolicyNote.getStatus());
			note.setAddDt(legacyPolicyNote.getAddDt());
			note.setAddTm(legacyPolicyNote.getAddTm());
			note.setAddUser(legacyPolicyNote.getAddUser());
			if(legacyPolicyNote.getStickyInd() != null && "yes".equalsIgnoreCase(legacyPolicyNote.getStickyInd())){
				note.setStickyInd(INDICATOR_YES);
			}
			
			LinkReference linkRef = new LinkReference();
			linkRef.setId(note.getId() + "-LinkReference");
			linkRef.setIdRef(basicPolicy.getId());
			linkRef.setModelName(POLICY_RECORD_STATUS_POLICY);
			linkRef.setStatus(ACTIVE);
			note.setLinkReference(linkRef);
			
			Tag tag = new Tag();
			tag.setId(note.getId() + "-Tag");
			tag.setName("Correspondence");
			tag.setTagTemplateIdRef("Correspondence");
			note.setTag(tag);
			
			app.getNote().add(note);
		}
	}
	
	/**
	 * @param dtoApplication
	 * @param rewrites
	 */
	public static void applyRewriteRenewals(DTOApplication app, DTOApplication previousApp, List<routines.LegacyRewrite> rewrites) {
		DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
		DTOTransactionInfo dtoTransactionInfo = app.getDTOTransactionInfo();
		for (routines.LegacyRewrite rewrite : rewrites) {
			//System.out.println("Policy:" + basicPolicy.getPolicyDisplayNumber() + "==> NEW is " + rewrite.getNEWPOLICYNUMBER() + " OLD is " + rewrite.getOLDPOLICYNUMBER());
			
			// Changed on 06/19/2015
			/*
			DTOApplication/DTOBasicPolicy/@TransactionCd – Changed from ‘Renewal’ to ‘Rewrite-Renewal’
			DTOApplication/DTOTransactionInfo/@TransactionCd – Changed from ‘Renewal’ to ‘Rewrite-Renewal’
			DTOApplication/DTOBasicPolicy/@RenewedFromPolicyNumber – Removed this field since it is not valid for ‘Rewrite-Renewal’
			DTOApplication/DTOBasicPolicy/@RewriteFromPolicyNumber – Added this field since it is required for ‘Rewrite-Renewal’ and set the value from the ‘RenewedFromPolicyNumber’ field - ALZ5003244-03
			 */
			dtoTransactionInfo.setTransactionCd(TRANS_CODE_REWRITE_RENEWAL);
			dtoTransactionInfo.setRewriteToProductVersion(basicPolicy.getProductVersionIdRef());
			dtoTransactionInfo.setTransactionEffectiveDt(basicPolicy.getEffectiveDt());
			dtoTransactionInfo.setBookDt(dtoTransactionInfo.getTransactionEffectiveDt());
			dtoTransactionInfo.setTransactionShortDescription(TRANS_CODE_REWRITE_RENEWAL);
			
			/*
			if(rewrite.getENDORSEMENTDATE() != null){
				synchronized (DATE_FRMT) {
					basicPolicy.setExpirationDt(DATE_FRMT.format(rewrite.getENDORSEMENTDATE()));
				}
			}
			*/
			basicPolicy.setTransactionCd(dtoTransactionInfo.getTransactionCd());
			
			if(basicPolicy.getRenewedFromPolicyNumber() != null){
				basicPolicy.setRewriteFromPolicyNumber(basicPolicy.getRenewedFromPolicyNumber());
			}
			else{
				if(previousApp != null){
    				basicPolicy.setRewriteFromPolicyNumber(previousApp.getDTOBasicPolicy().getPolicyDisplayNumber());
				}
			}
			
			basicPolicy.setRenewedFromPolicyNumber(null);
			
			generated.DTOTransactionReason transReason = dtoTransactionInfo.getDTOTransactionReason();
	    	if(transReason != null){
	    		transReason.setId(transReason.getId() + "-" + RandomStringUtils.randomAlphanumeric(7));
	    	}
		}
	}
	
	/**
	 * @param dtoApplication
	 * @param exp
	 */
	public static void convertToExpiration(DTOApplication app, routines.LegacyPolicyExpiration exp) {
		app.setTypeCd(TYPE_CD_APPLICATION);
    	app.setStatus(APP_STATUS_IN_PROCESS);
    	app.setDescription(APP_DESCRIPTION_NEW_QUOTE);
    	
    	DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
    	basicPolicy.setTransactionCd(TRANSACTION_CD_EXPIRE);
    	basicPolicy.setStatusCd(TRANSACTION_CD_EXPIRE);
    	basicPolicy.setRenewedFromPolicyNumber(null);
    	basicPolicy.setConversionWrittenPremiumAmt(0.0);
    	
		DTOTransactionInfo transInfo = new DTOTransactionInfo();
		transInfo.setTransactionCd(TRANSACTION_CD_EXPIRE);
		transInfo.setTransactionShortDescription(TRANSACTION_CD_EXPIRE);
		transInfo.setSourceCd(PETPLAN_SOURCE_CD);
		transInfo.setTransactionUser(USER_CONVERSION);
		transInfo.setId(basicPolicy.getId() + "-DTOTransactionInfo-" + transInfo.getTransactionCd());
		
		synchronized (DATE_FRMT) {
    		if(exp.getEXPIRATION_DATE() != null){
    			transInfo.setTransactionEffectiveDt(DATE_FRMT.format(exp.getEXPIRATION_DATE()));
			}
		}
		
		app.setDTOTransactionInfo(transInfo);
	}
	
    /**
	 * @param dtoApplication
	 * @param cancelNotice
	 */
	public static void convertToCancelNotice(DTOApplication app, LegacyCancelationNotice legacyCancelNotice) {
		app.setTypeCd(TRANSACTION_CD_CANCELLATION_NOTICE);
    	app.setDescription(TRANS_CODE_CANCELLATION);
    	
    	DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
    	basicPolicy.setTransactionCd(TRANSACTION_CD_CANCELLATION_NOTICE);
    	basicPolicy.setStatusCd(STATUS_CD_CANCELLATION_NOTICE);
    	
    	// Increment transaction number.
    	//basicPolicy.setTransactionNumber(new BigInteger(String.valueOf(basicPolicy.getTransactionNumber().intValue() + 1)));
    	
    	// Increment policy version.
    	// Changed on 03052015
    	//setPolicyVersion(app, policyData, null);
    	
    	// Start transaction number with 2.
    	int index = 2;

		DTOTransactionHistory transHistory = new DTOTransactionHistory();
		transHistory.setTransactionNumber(new BigInteger(String.valueOf(index)));
		transHistory.setId(basicPolicy.getId() + "-TransactionHistory-" + (index));
		transHistory.setTransactionCd(TRANSACTION_CD_CANCELLATION_NOTICE);
		transHistory.setTransactionShortDescription(TRANS_CODE_CANCELLATION);
		transHistory.setSourceCd(PETPLAN_SOURCE_CD);
		transHistory.setTransactionUser(USER_CONVERSION);
		transHistory.setCancelTypeCd(legacyCancelNotice.getCANCEL_TYPE_CODE());
		transHistory.setCancelRequestedByCd(legacyCancelNotice.getCANCEL_REQUEST_BY_CODE());
		
		synchronized (DATE_FRMT) {
    		if(legacyCancelNotice.getEFFECTIVEDATE() != null){
    			basicPolicy.setCancelDt(DATE_FRMT.format(legacyCancelNotice.getEFFECTIVEDATE()));
    			transHistory.setTransactionEffectiveDt(basicPolicy.getCancelDt());
			}
    		
		}
		
		// Set transaction info.
    	/*DTOTransactionInfo transInfo = app.getDTOTransactionInfo();
    	transInfo.setTransactionCd(TRANSACTION_CD_ENDORSEMENT);
    	transInfo.setTransactionShortDescription(TRANSACTION_CD_ENDORSEMENT);
    	transInfo.setTransactionEffectiveDt(basicPolicy.getEffectiveDt());
    	*/
		
    	// Add transaction text to history.
    	DTOTransactionReason transText = new DTOTransactionReason();
		transHistory.setDTOTransactionReason(transText);
		transText.setId(transHistory.getId() + "-TransactionReason-1");
		transText.setReasonCd("InsuredRequest");
		
		basicPolicy.getDTOTransactionHistory().add(transHistory);
	}
	
	/**
	 * @param dtoApplication
	 * @param policyHold
	 */
	public static void convertToHold(DTOApplication app, LegacyPolicyHold policyHold) {
		app.setTypeCd(TRANSACTION_CD_PLACE_ON_HOLD);
    	
    	DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
    	basicPolicy.setTransactionCd(TRANSACTION_CD_PLACE_ON_HOLD);
    	
    	// Increment transaction number.
    	//basicPolicy.setTransactionNumber(new BigInteger(String.valueOf(basicPolicy.getTransactionNumber().intValue() + 1)));
    	
    	// Increment policy version.
    	// Changed on 03052015
    	//setPolicyVersion(app, policyData, null);
    	
    	// Start transaction number with 2.
    	int index = 2;

		DTOTransactionHistory transHistory = new DTOTransactionHistory();
		transHistory.setTransactionNumber(new BigInteger(String.valueOf(index)));
		transHistory.setId(basicPolicy.getId() + "-TransactionHistory-" + (index));
		transHistory.setTransactionCd(TRANSACTION_CD_PLACE_ON_HOLD);
		transHistory.setTransactionShortDescription(TRANSACTION_CD_PLACE_ON_HOLD);
		transHistory.setSourceCd(PETPLAN_SOURCE_CD);
		transHistory.setTransactionUser(USER_CONVERSION);
		transHistory.setHoldType("All");
		
		synchronized (DATE_FRMT) {
    		if(policyHold.getEFFECTIVEDATE() != null){
    			transHistory.setTransactionEffectiveDt(DATE_FRMT.format(policyHold.getEFFECTIVEDATE()));
			}
		}
		
		// Set transaction info.
    	/*DTOTransactionInfo transInfo = app.getDTOTransactionInfo();
    	transInfo.setTransactionCd(TRANSACTION_CD_ENDORSEMENT);
    	transInfo.setTransactionShortDescription(TRANSACTION_CD_ENDORSEMENT);
    	transInfo.setTransactionEffectiveDt(basicPolicy.getEffectiveDt());
    	*/
		
    	// Add transaction text to history.
    	DTOTransactionReason transText = new DTOTransactionReason();
		transHistory.setDTOTransactionReason(transText);
		transText.setId(transHistory.getId() + "-TransactionReason-1");
		transText.setReasonCd("InsuredRequest");
		
		basicPolicy.getDTOTransactionHistory().add(transHistory);
	}
	
    /**
     * @param app
     * @param claimData
     */
    public static void mapAppClaims(int claimIndex, DTOApplication app, LegacyClaimData claimData){
    	DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
    	String policyNumber = basicPolicy.getPolicyNumber();
    	
    	if(claimData != null){
    		DTOLossHistory lossHistory = new DTOLossHistory();
    		lossHistory.setId(basicPolicy.getId() + "-LossHistory-" + claimIndex);
    		
    		app.getDTOLossHistories().add(lossHistory);
    		
    		lossHistory.setLossHistoryNumber(new BigInteger(String.valueOf(app.getDTOLossHistories().size())));
    		lossHistory.setStatusCd(getLossHistoryStatusCd(claimData));
    		lossHistory.setSourceCd(SOURCE_CODE_CLAIM);
    		synchronized (DATE_FRMT) {
    			if(claimData.getIncident_Date() == null){
    				lossHistory.setLossDt(DATE_FRMT.format(new Date()));
    			}
    			else{
    				lossHistory.setLossDt(DATE_FRMT.format(claimData.getIncident_Date()));
    			}
			}
    		if(claimData.getReserve_Amount() != null){
    			lossHistory.setLossAmt(new Double(claimData.getReserve_Amount().toString()));
    		}
    		if(claimData.getPaid_Amount() != null){
    			lossHistory.setPaidAmt(new Double(claimData.getPaid_Amount().toString()));
    		}
    		lossHistory.setClaimNumber(claimData.getClaim_Number());
    		lossHistory.setClaimStatusCd(getClaimStatusCd(claimData));
    		lossHistory.setPolicyNumber(claimData.getPolicy_Number());
    		lossHistory.setPolicyTypeCd(getPolicyTypeCd(claimData));
    		lossHistory.setLossCauseCd(getLossCauseCd(claimData));
    		lossHistory.setLossDesc(claimData.getCondition());
    		lossHistory.setTypeCd(getLossHistoryTypeCd(claimData));
    		
    		/*
    		 <DTOLossHistory id="HO078002-LossHistory-Seq-1" 
    		 LossHistoryNumber="1" 
    		 StatusCd="Active" 
    		 SourceCd="Claim" 
    		 LossDt="19940903" 
    		 LossAmt="365.04" L
    		 lossDesc="Ins Boat Hit Obj Under Water" 
    		 ClaimNumber="CLM8099397" 
    		 ClaimStatusCd="Closed" 
    		 PolicyNumber="HO051819" 
    		 PaidAmt="0" 
    		 LossCauseCd="Other" 
    		 PolicyTypeCd="Homeowners" 
    		 TypeCd="Property" RiskIdRef="HO078002-Line-Risk" />
    		 */
    	}
    }
    
    /**
     * @param app
     * @param taxesCharged
     */
    public static void mapTax(DTOApplication app, BigDecimal taxesCharged){
    	if(taxesCharged != null && taxesCharged.doubleValue() > 0D){
    		DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
    		if((basicPolicy.getFullTermAmt() != null && basicPolicy.getFullTermAmt().doubleValue() > 0D)){
	        	// Handle cancellation.
	        	/*if(TRANS_CODE_CANCELLATION.equalsIgnoreCase(app.getDTOTransactionInfo().getTransactionCd())){
	    			double newAmount = basicPolicy.getFullTermAmt().doubleValue() - taxesCharged.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		    		newAmount = new BigDecimal(newAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		    		//System.out.println(basicPolicy.getFullTermAmt().doubleValue() + "-" + taxesCharged.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "=" + newAmount);
		    		basicPolicy.setFullTermAmt(newAmount);
		        	basicPolicy.setWrittenPremiumAmt(newAmount);
		        	basicPolicy.setFinalPremiumAmt(newAmount);
	        	}*/
    		}
    	}
    }
    
    /**
     * @param app
     * @param customerRef
     */
    public static void mapCustomerRef(DTOApplication app, BigInteger customerRef){
    	app.setCustomerRef(customerRef);
    }
    		
    /**
     * @param policyNumber
     * @return
     */
    public static String getPolicyNumber(String policyNumber){
    	return policyNumber.substring(0, policyNumber.lastIndexOf('-'));
    }
    
    /**
     * @param transData
     * @return
     */
    public static String getPayByCode(LegacyTransactionData transData){
    	if(transData != null && "MANREFUND".equals(transData.getPAYMETHOD_ID())){
    		return null;
    	}
    	return "Insured"; //, Mortgagee, Producer, Other
    }

    /**
     * @param transData
     * @return
     */
    public static String getAdjustmentCategoryCd(LegacyTransactionData transData){
    	if("".equals(transData.getPAYMETHOD_ID())){
    		return "Policy Fee";
    	}
    	else if("".equals(transData.getPAYMETHOD_ID())){
    		return "Endorsement Fee";
    	}
    	else if("".equals(transData.getPAYMETHOD_ID())){
    		return "Installment Fee";
    	}
    	else if("".equals(transData.getPAYMETHOD_ID())){
    		return "Late Fee";
    	}
    	else if("".equals(transData.getPAYMETHOD_ID())){
    		return "Tax";
    	}
    	else{
    		return "Premium";
    	}
    }
    
    /**
     * @param transData
     * @return
     */
    public static String getAdjustmentTypeCd(double difference){
    	if(difference < 0D){
    		return WAIVE_CREDIT;
    	}
    	else{
    		return WAIVE_DEBIT;
    	}
    }
    
    /**
     * @param transData
     * @return
     */
    public static String getARReceiptTypeCd(LegacyTransactionData transData){
    	if("WOFF".equals(transData.getPAYMETHOD_ID())){
    		return WAIVE_DEBIT;
    	}
    	else if("MANREFUND".equals(transData.getPAYMETHOD_ID())){
    		return CHECK;
    	}
    	else{
    		return CASH;
    	}
    }
    
    /**
     * @param claimData
     * @return
     */
    public static String getLossCauseCd(LegacyClaimData claimData){
    	String circumstances = claimData.getCIRCUMSTANCES();
    	return "Other";
    }
    
    /**
     * @param claimData
     * @return
     */
    public static String getPolicyTypeCd(LegacyClaimData claimData){
    	return POLICY_SUBTYPE_PLP;
    }
    
    /**
     * @param claimData
     * @return
     */
    public static String getClaimStatusCd(LegacyClaimData claimData){
    	return claimData.getOpen___Closed();
    	//return "Closed";
    }
    
    /**
     * @param claimData
     * @return
     */
    public static String getLossHistoryStatusCd(LegacyClaimData claimData){
    	return claimData.getCLOSUREDATE() != null ? "Inactive" : ACTIVE;
    }
    
    /**
     * @param claimData
     * @return
     */
    public static String getLossHistoryTypeCd(LegacyClaimData claimData){
    	return "Property";
    }
    
    /**
     * @param policyData
     * @return
     */
    public static String getPolicyForm(LegacyPolicyData policyData){
    	return policyData.scheme != null ? policyData.scheme.short_name : POLICY_FORM_PLATINUM;
    }
    
    /**
     * @param policyData
     * @return
     */
    public static String getDTOInsuredEntityTypeCd(LegacyPolicyData policyData){
    	return "Individual";
    }
    
    /**
     * @param policyData
     * @return
     */
    public static String getPreferredDeliveryMethod(LegacyPolicyData policyData){
    	return "Email";
    }
    
    /**
     * @param policyData
     * @return
     */
    public static String getProgramCode(LegacyPolicyData policyData){
    	return "NonCivilServant";
    }
    
    /**
     * @param policyData
     * @return
     */
    public static String getPersonTypeCode(LegacyPolicyData policyData){
    	return "InsuredPersonal";
    }
    
    /**
     * @param policyData
     * @param policy
     * @param productVersion
     * @return
     */
    public static String getPayPlanCode(LegacyPolicyData policyData, DTOBasicPolicy policy, ProductVersion productVersion, boolean isRenewal, boolean isRewritten){
    	LegacyScheme scheme = policyData.scheme;
    	//boolean newPolicy = policyData.POLICYNUMBER.endsWith("-00");
    	String gType = "DC, MN, NH, NY, PA".contains(policyData.getCOUNTY()) ? G2_PAY_PLAN_TYPE 
    			: ("AK, MT, SD".contains(policyData.getCOUNTY()) ? G1_PAY_PLAN_TYPE : " ");
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(policyData.getPOLICYSTARTDATE());
    	String year = "2013".equals(scheme.PayPlan) ? "2013 " : "2012 "; //cal.get(Calendar.YEAR) == 2013 ? "2013 " : "";
    	if(scheme.PayPlan.equals("2014") || scheme.PayPlan.equals("2015")){
    		year = "2013 ";
    	}
    	String renewal = isRenewal ? " Renewal" : "";
    	String code = "";
    	
    	final String diffBillDt = "Diff Bill Dt ";
    	boolean needsDiffBillDt = policyData.getDIFF_BILL() != null && 1 == policyData.getDIFF_BILL();
		
    	// Full pay 
    	Calendar eighteenMonthsAgo = Calendar.getInstance();
    	eighteenMonthsAgo.add(Calendar.MONTH, -18);
    	
    	//if(!POLICY_RECORD_STATUS_POLICY.equalsIgnoreCase(policyData.record_status)){
    	if(isRewritten || "v00-00-00".equalsIgnoreCase(policyData.getProductVersionIDRef())){	//policyData.getPOLICYSTARTDATE().before(eighteenMonthsAgo.getTime())){
    		code = "PLP" + gType + "Automated Full Pay";
    	}
    	else{
    		//System.out.println("Payment Plan Name:" + policyData.PAYMENT_PLAN_NAME + " and " + policyData.PAYMENT_PLAN_ID);
	    	if(policyData.PAYMENT_PLAN_NAME.contains("Monthly")){
	    		// Monthly plans are ALWAYS 'legacy'.
	    		// Changed on 02260215 because Alex felt like it on this great sunny day.
	    		/*if(policyData.getPOLICYSTARTDATE().before(eighteenMonthsAgo.getTime())){
    				code = "PLP" + gType + "Automated Full Pay Credit Card";
    			}*/
	    		//if(POLICY_RECORD_STATUS_POLICY.equalsIgnoreCase(policyData.record_status)){
	    		//else{
	    			code = "PLP" + gType + "Automated " + (needsDiffBillDt ? diffBillDt : "") + "12 Pay Credit Card Legacy";
	    			renewal = "";
	    		//}
	    	}
	    	else if(policyData.PAYMENT_PLAN_NAME.contains("Quarterly")){
	    		code = "PLP" + gType + "Automated " + (needsDiffBillDt ? diffBillDt : "") + "4 Pay Credit Card" + (year.contains("2013") ? " Legacy" : "");
	    		renewal = "";
	    	}
	    	//Automated Full Pay Credit Card
	    	else if(policyData.PAYMENT_PLAN_NAME.contains("Annual")){
	    		code = "PLP" + gType + "Automated Full Pay Credit Card";
	    	}
	    	else{ // Annually.
	    		code = "PLP" + gType + "Automated Full Pay";
	    	}
    	}
    	
    	return year + code + renewal;
    	//return policyData.getSpi_payment_plan();
    }
    
    /**
     * @param policyData
     * @return
     */
    public static String getRenewalTermCode(LegacyPolicyData policyData){
    	return "1 Year";
    }
    
    /**
     * @param policyData
     * @return
     */
    public static DTOCustomer mapCustomerData(LegacyPolicyData policyData){
    	DTOCustomer dtoCustomer = new DTOCustomer();
    	dtoCustomer.setPassword(policyData.getPASSWORD());
    	dtoCustomer.setIpid(policyData.getINSURED_PARTY_ID());
    	if(StringUtils.isBlank(dtoCustomer.getIpid())){
    		dtoCustomer.setId(RandomStringUtils.randomAlphanumeric(20));
    	}
    	else{
    		dtoCustomer.setId(dtoCustomer.getIpid());
    	}
    	
    	//dtoCustomer.setCustomerNumber(policyData.g);
    	dtoCustomer.setIndexName(policyData.getSURNAME() + ", " + policyData.getFORENAME());
    	dtoCustomer.setPreferredDeliveryMethod("Email");
    	dtoCustomer.setEntityTypeCd("Individual");
    	dtoCustomer.setStatus(ACTIVE);
    	dtoCustomer.setUpdateCount("0");
    	dtoCustomer.setVipLevel(String.valueOf(policyData.getVIPLevel()));
    	
    	PartyInfo partyInfo = new PartyInfo();
    	partyInfo.setPartyTypeCd(PARTY_TYPE_CD_CUSTOMER_PARTY);
    	dtoCustomer.getPartyInfo().add(partyInfo);
    	
    	PersonInfo personInfo = new PersonInfo();
    	personInfo.setPersonTypeCd("CustomerPersonal");
    	partyInfo.setPersonInfo(personInfo);
    	
    	EmailInfo emailInfo = new EmailInfo();
    	emailInfo.setEmailTypeCd(EMAIL_TYPE_CODE_CUSTOMER_EMAIL);
    	emailInfo.setPreferredInd(INDICATOR_YES);
    	emailInfo.setEmailAddr(policyData.getEMAIL());
    	partyInfo.getEmailInfo().add(emailInfo);
    	
    	NameInfo nameInfo = new NameInfo();
    	nameInfo.setNameTypeCd(NAME_TYPE_CODE_CUSTOMER_NAME);
    	nameInfo.setGivenName(policyData.getFORENAME());
    	nameInfo.setSurname(policyData.getSURNAME());
    	nameInfo.setCommercialName(dtoCustomer.getIndexName());
    	partyInfo.setNameInfo(nameInfo);
    	
    	Addr addr = new Addr();
    	addr.setAddrTypeCd(ADDR_TYPE_CODE_CUSTOMER_MAILING_ADDR);
    	addr.setAddr1(policyData.getSTREET2());
    	addr.setCity(policyData.getCITY2());
    	addr.setStateProvCd(policyData.getCOUNTY2());
    	addr.setPostalCode(policyData.getPOSTCODE2());
    	processAddr(addr);
    	partyInfo.getAddrs().add(addr);
    	
    	addr = new Addr();
    	addr.setAddrTypeCd(ADDR_TYPE_CODE_CUSTOMER_BILLING_ADDR);
    	addr.setAddr1(policyData.getSTREET2());
    	addr.setCity(policyData.getCITY2());
    	addr.setStateProvCd(policyData.getCOUNTY2());
    	addr.setPostalCode(policyData.getPOSTCODE2());
    	processAddr(addr);
    	partyInfo.getAddrs().add(addr);
    	
    	addr = new Addr();
    	addr.setAddrTypeCd(ADDR_TYPE_CODE_CUSTOMER_LOOKUP_ADDR);
    	/*
    	// PreDirectional="Ne" PrimaryNumber="4227" StreetName="10th" Suffix
    	String addressParts[] = policyData.getSTREET().split(" ");
    	parseAddress(addressParts, addr);
    	*/
    	addr.setAddr1(policyData.getSTREET());
    	addr.setCity(policyData.getCITY());
    	addr.setStateProvCd(policyData.getCOUNTY());
    	addr.setPostalCode(policyData.getPOSTCODE());
    	processAddr(addr);
    	partyInfo.getAddrs().add(addr);
    	
    	TaxInfo taxInfo = new TaxInfo();
    	taxInfo.setTaxTypeCd(TAX_TYPE_CODE_CUSTOMER_TAX_INFO);
    	taxInfo.setTaxIdTypeCd(TAX_ID_TYPE_CODE_SSN);
    	partyInfo.setTaxInfo(taxInfo);
    	
    	/*
    	<PartyInfo id="PartyInfo-1146138615-223388715" PartyTypeCd="CustomerPartyJoint">
    		<PersonInfo id="PersonInfo-2088018648-1299141309" PersonTypeCd="CustomerPersonalJoint" />
    		<TaxInfo id="TaxInfo-1201741314-582564363" TaxTypeCd="CustomerTaxInfoJoint" />
    		<NameInfo id="NameInfo-692718049-1003399156" NameTypeCd="CustomerNameJoint" />
    	</PartyInfo>
    	<PartyInfo id="PartyInfo-781302268-1632200518" PartyTypeCd="CustomerPartyTrust">
    		<TaxInfo id="TaxInfo-437999248-2026902867" TaxTypeCd="CustomerTaxInfoTrust" />
    	</PartyInfo>
    	<PartyInfo id="PartyInfo-623393308-1946097490" PartyTypeCd="CustomerPartyEstate">
    		<TaxInfo id="TaxInfo-1884570620-742100737" TaxTypeCd="CustomerTaxInfoEstate" />
    	</PartyInfo>
    	*/
    	
    	PartyInfo jointParty = new PartyInfo();
        jointParty.setPartyTypeCd("CustomerPartyJoint");
        jointParty.setId(dtoCustomer.getId() + "-PartyInfo-" + jointParty.getPartyTypeCd());
        dtoCustomer.getPartyInfo().add(jointParty);
        
        personInfo = new PersonInfo();
    	personInfo.setPersonTypeCd("CustomerPersonalJoint");
    	personInfo.setId(jointParty.getId() + "-PersonInfo-" + personInfo.getPersonTypeCd());
    	jointParty.setPersonInfo(personInfo);
    	
    	taxInfo = new TaxInfo();
    	taxInfo.setTaxTypeCd("CustomerTaxInfoJoint");
    	taxInfo.setId(jointParty.getId() + "-TaxInfo-" + taxInfo.getTaxTypeCd());
    	jointParty.setTaxInfo(taxInfo);
    	
    	nameInfo = new NameInfo();
    	nameInfo.setNameTypeCd("CustomerNameJoint");
    	nameInfo.setId(jointParty.getId() + "-NameInfo-" + nameInfo.getNameTypeCd());
    	jointParty.setNameInfo(nameInfo);
    	
    	PartyInfo trustParty = new PartyInfo();
    	trustParty.setPartyTypeCd("CustomerPartyTrust");
    	trustParty.setId(dtoCustomer.getId() + "-PartyInfo-" + trustParty.getPartyTypeCd());
    	dtoCustomer.getPartyInfo().add(trustParty);
    	
    	taxInfo = new TaxInfo();
    	taxInfo.setTaxTypeCd("CustomerTaxInfoTrust");
    	taxInfo.setId(trustParty.getId() + "-TaxInfo-" + taxInfo.getTaxTypeCd());
    	trustParty.setTaxInfo(taxInfo);
	    
    	PartyInfo estateParty = new PartyInfo();
    	estateParty.setPartyTypeCd("CustomerPartyEstate");
    	estateParty.setId(dtoCustomer.getId() + "-PartyInfo-" + estateParty.getPartyTypeCd());
    	dtoCustomer.getPartyInfo().add(estateParty);
    	
    	taxInfo = new TaxInfo();
    	taxInfo.setTaxTypeCd("CustomerTaxInfoEstate");
    	taxInfo.setId(estateParty.getId() + "-TaxInfo-" + taxInfo.getTaxTypeCd());
    	estateParty.setTaxInfo(taxInfo);

    	// Reset.
    	dtoCustomer.setId(null);
    	//Contact contact = new Contact();
    	//dtoCustomer.setContact(contact);
    	return dtoCustomer;
    }
    
    /**
     * @param addressParts
     * @param addr
     */
    public static void parseAddress(String [] addressParts, Addr addr){
    	if(addressParts != null && addressParts.length > 0){
    		addr.setPrimaryNumber(addressParts[0]);
    		
    		// Suffix is the last part of address.
    		if(addressParts.length > 1){
    			//addr.setSuffix(addressParts[addressParts.length-1]);
    		}
    		
    		if(addressParts.length > 3){
    			String streetArr[] = Arrays.copyOfRange(addressParts, 1, addressParts.length-1);
    			addr.setStreetName(StringUtils.join(streetArr, ' '));
    			
    			for (int i = 1; i < addressParts.length; i++) {
    				String loweredPart = addressParts[i].toLowerCase();
    				if("north n. ne n.e. nw n.w. s. south se s.e. sw s.w. s. west w. east e.".contains(loweredPart)){
    					if(i == 1){
    						addr.setPreDirectional(addressParts[i]);
    						addr.setStreetName(addr.getStreetName().replace(addr.getPreDirectional(), "").trim());
    					}
    					else{
    						addr.setPostDirectional(addressParts[i]);
    						addr.setStreetName(addr.getStreetName().replace(addr.getPostDirectional(), "").trim());
    					}
    					break;
    				}
    			}
    			
    		}
    		else if(addressParts.length == 3){
    			addr.setStreetName(addressParts[1]);
    		}
    	}
    }
    
    /**
     * @param insured
     * @param type
     * @return
     */
    public static generated.PartyInfo getPartyInfo(generated.DTOCustomer customer, String type){
    	List<generated.PartyInfo> partyInfos = customer.getPartyInfo();
    	for (PartyInfo partyInfo : partyInfos) {
			if(type.equalsIgnoreCase(partyInfo.getPartyTypeCd())){
				return partyInfo;
			}
		}
    	return null;
    }
    
    /**
     * @param insured
     * @param type
     * @return
     */
    public static generated.PartyInfo getPartyInfo(generated.DTOInsured insured, String type){
    	List<generated.PartyInfo> partyInfos = insured.getPartyInfo();
    	for (PartyInfo partyInfo : partyInfos) {
			if(type.equalsIgnoreCase(partyInfo.getPartyTypeCd())){
				return partyInfo;
			}
		}
    	return null;
    }
    
    /**
     * @param insured
     * @param type
     * @return
     */
    public static generated.PartyInfo getPartyInfo(generated.Insured insured, String type){
    	List<generated.PartyInfo> partyInfos = insured.getPartyInfos();
    	for (PartyInfo partyInfo : partyInfos) {
			if(type.equalsIgnoreCase(partyInfo.getPartyTypeCd())){
				return partyInfo;
			}
		}
    	return null;
    }
    
    /**
     * @param fee
     * @param type
     * @return
     */
    public static generated.Step getFeeStep(generated.Fee fee, String type){
    	List<generated.Steps> stepsList = fee.getSteps();
    	for (generated.Steps stepsObj : stepsList) {
    		List<generated.Step> steps = stepsObj.getStep();
    		if(steps != null){
    			return steps.get(0);
	    		/*for (generated.Step step2 : steps) {
	    			if(type.equalsIgnoreCase(step2.getName())){
	    				return step2;
	    			}
				}*/
    		}
		}
    	return null;
    }
    
    /**
     * @param fee
     * @param type
     * @return
     */
    public static generated.Step getStep(generated.Steps stepsObj, String type){
    	List<generated.Step> steps = stepsObj.getStep();
    	for (generated.Step step : steps) {
    		if(type.equalsIgnoreCase(step.getName())){
    			return step;
    		}
    		else{
    			List<generated.Steps> stepsOfStep = step.getSteps();
    			for (generated.Steps stepsObj2 : stepsOfStep) {
    				generated.Step foundStep = getStep(stepsObj2, type);
					if(foundStep != null){
						return foundStep;
					}
				}
    		}
		}
		return null;
    }
    
    
    /**
     * @param risk
     * @param coverageCd
     * @return
     */
    public static generated.Coverage getRiskCoverage(generated.Risk risk, String coverageCd){
    	for (generated.Coverage c : risk.getCoverage()) {
			if(coverageCd.equalsIgnoreCase(c.getCoverageCd())){
				return c;
			}
		}
    	return null;
    }
    
    /**
     * @param partyInfo
     * @param type
     * @return
     */
    public static generated.Addr getPartyAddr(generated.PartyInfo partyInfo, String type){
    	for (generated.Addr addr : partyInfo.getAddrs()) {
			if(type.equalsIgnoreCase(addr.getAddrTypeCd())){
				return addr;
			}
		}
    	return null;
    }
    
    /**
     * @param partyInfo
     * @param type
     * @return
     */
    public static generated.PhoneInfo getPartyPhone(generated.PartyInfo partyInfo, String type){
    	for (generated.PhoneInfo phoneInfo : partyInfo.getPhoneInfos()) {
			if(type.equalsIgnoreCase(phoneInfo.getPhoneTypeCd())){
				return phoneInfo;
			}
		}
    	return null;
    }
    
    /**
     * @param partyInfo
     * @param type
     * @return
     */
    public static generated.EmailInfo getPartyEmail(generated.PartyInfo partyInfo, String type){
    	for (generated.EmailInfo emailInfo : partyInfo.getEmailInfo()) {
			if(type.equalsIgnoreCase(emailInfo.getEmailTypeCd())){
				return emailInfo;
			}
		}
    	return null;
    }
    
    
    /**
     * @param qReplies
     * @param type
     * @return
     */
    public static generated.QuestionReply getQuestionReply(QuestionReplies qReplies, String type){
    	for (generated.QuestionReply qr : qReplies.getQuestionReply()) {
			if(type.equalsIgnoreCase(qr.getName())){
				return qr;
			}
		}
    	return null;
    }
    
    /**
     * @param globalMap
     * @return
     */
    public static String getDTOApplicationForQuote(Map<String, Object> globalMap){
    	//long now = System.currentTimeMillis();
    	Map<String, List<routines.LegacyCoverage>> coverageByPolicy = (Map<String, List<routines.LegacyCoverage>>) globalMap.get("coverageByPolicy");
    	Map<String, routines.LegacyPolicyData> policyDataByPolicyNumber = (Map<String, routines.LegacyPolicyData>) globalMap.get("policyDataByPolicyNumber");
    	Map<String, java.math.BigDecimal> taxesByPolicyNumbers = (Map<String, java.math.BigDecimal>) globalMap.get("taxesByPolicyNumbers");

    	List<generated.DTOApplication> apps = (List<generated.DTOApplication>) globalMap.get("applications");
    	generated.DTOApplication origApp = apps.get(((Integer)globalMap.get("tLoop_3_CURRENT_ITERATION"))-1);
    	
    	// Make a deep copy.
    	generated.DTOApplication app = fromXml2DTOApplication(toXml(origApp));
    	app.setTypeCd(TYPE_CD_QUOTE);
    	app.setCustomerRef(null);
    	app.setExternalStateData(null);
    	app.setId(null);
    	
    	DTOBasicPolicy basicPolicy = app.getDTOBasicPolicy();
    	basicPolicy.setTransactionCommissionAmt(null);
    	basicPolicy.setWrittenCommissionFeeAmt(null);
    	basicPolicy.setWrittenFeeAmt(null);
    	basicPolicy.setCommission(null);
    	basicPolicy.setFullTermAmt(null);
    	basicPolicy.setWrittenPremiumAmt(null);
    	basicPolicy.setFinalPremiumAmt(null);
    	
    	DTOTransactionInfo transInfo = app.getDTOTransactionInfo();
    	transInfo.setRenewalDiscountAmt(null);
    	
    	routines.Utils.validatePrimaryTelephone(app);
	  	
	  	routines.LegacyPolicyData policyData_curr = policyDataByPolicyNumber.get(app.getDTOBasicPolicy().getPolicyDisplayNumber());
	  	
	  	// NOTE: Perform coverage mapping.
	  	List<routines.LegacyCoverage> planCovarages = coverageByPolicy.get(app.getDTOBasicPolicy().getPolicyDisplayNumber());
	  	if(planCovarages != null && !planCovarages.isEmpty()){
	  		app.setExternalStateData(policyData_curr.getTRANSACTION_GROUP().toString());
	  		
	  		DTOApplication previousApp = null;
    		/*if((((Integer)globalMap.get("tLoop_3_CURRENT_ITERATION"))-1) > 0){
    			previousApp = apps.get(((Integer)globalMap.get("tLoop_3_CURRENT_ITERATION"))-2);
    		}*/
			routines.Utils.mapCoverage(app, previousApp, planCovarages, policyData_curr);
			
			// Reset.
			app.setExternalStateData(null);
	  	}
	  	else{
	  		System.err.println("XXX Failed to obtain Coverage data for policy:" + app.getDTOBasicPolicy().getPolicyDisplayNumber());
	  	}
	  	
	  	boolean cancelation = routines.Utils.TRANS_CODE_CANCELLATION.equalsIgnoreCase(transInfo.getTransactionCd());
	  	if(cancelation){
	  		//routines.Utils.setCancellationPremiums(app);
	  	}
	  	
	  	// Handle canceled policies.
	  	if(cancelation){
	  		routines.LegacyPolicyData policyData_prev = null;
	  		DTOApplication previousApp = null;
    		if((((Integer)globalMap.get("tLoop_3_CURRENT_ITERATION"))-1) > 0){
    			previousApp = apps.get(((Integer)globalMap.get("tLoop_3_CURRENT_ITERATION"))-2);
    		}
	  		app.setExternalStateData(policyData_curr.getTRANSACTION_GROUP().toString());
	  		
	  		if(previousApp != null){
	  			policyData_prev = policyDataByPolicyNumber.get(previousApp.getDTOBasicPolicy().getPolicyDisplayNumber());
	  			previousApp.setExternalStateData(policyData_prev.getTRANSACTION_GROUP().toString());
	  		}
	  		
	  		
	  		routines.Utils.convertCanceledToRenewedOrNew(taxesByPolicyNumbers, policyData_curr, policyData_prev, app, previousApp, false);
	  		app.setExternalStateData(null);
	  	}
	  	
    	basicPolicy.setPolicyDisplayNumber(null);
    	basicPolicy.setPolicyNumber(null);
    	
    	String xml = toXml(app);
    	
    	//System.out.println(origApp.getDTOBasicPolicy().getPolicyDisplayNumber() + origApp.getDTOBasicPolicy().getFinalPremiumAmt() + "====>" + xml);
    	//System.out.println("Took:" + (System.currentTimeMillis() - now) + "ms");
    	return xml;
    }
    
    /**
     * @param dtoApplication
     * @param policyData
     * @param policyVersion
     */
    public static void setPolicyVersion(DTOApplication dtoApplication, LegacyPolicyData policyData, Integer policyVersion){
    	//int policyVersion = Integer.parseInt(policyData.POLICYNUMBER.substring(policyData.POLICYNUMBER.length() - 2));
    	//int policyVersion = Integer.parseInt(basicPolicy.getPolicyDisplayNumber().substring(basicPolicy.getPolicyDisplayNumber().length() - 2));
    	if(policyVersion != null){
    		dtoApplication.getDTOBasicPolicy().setPolicyVersion((((policyVersion) < 10) ? "00" : "0") + (policyVersion));
    	}
    }
    
    /**
     * @param xmlContent
     * @return
     */
    public static UWExternalPolicyListLoadRs fromXml2UWExternalPolicyListLoadRs(String xmlContent){
    	StringReader sr = new StringReader(xmlContent);
        try {
        	JAXBContext context = JAXB_EXTERNAL_POLICY_LIST_LOAD_RS;
            Unmarshaller marshaller = context.createUnmarshaller();
            return (UWExternalPolicyListLoadRs)marshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @param xmlContent
     * @return
     */
    public static generated.Policy fromXml2Policy(String xmlContent){
    	StringReader sr = new StringReader(xmlContent);
        try {
        	JAXBContext context = JAXB_POLICY;
            Unmarshaller marshaller = context.createUnmarshaller();
            return (generated.Policy)marshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @param xmlContent
     * @return
     */
    public static Customer fromXml2Customer(String xmlContent){
    	StringReader sr = new StringReader(xmlContent);
        try {
        	JAXBContext context = JAXB_CUSTOMER;
            Unmarshaller marshaller = context.createUnmarshaller();
            return (Customer)marshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @param xmlContent
     * @return
     */
    public static generated.Provider fromXml2Provider(String xmlContent){
    	StringReader sr = new StringReader(xmlContent);
        try {
        	JAXBContext context = JAXB_PROVIDER;
            Unmarshaller marshaller = context.createUnmarshaller();
            return (generated.Provider)marshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @param xmlContent
     * @return
     */
    public static DTOCustomer fromXml2DTOCustomer(String xmlContent){
    	StringReader sr = new StringReader(xmlContent);
        try {
        	JAXBContext context = JAXB_DTO_CUSTOMER;
            Unmarshaller marshaller = context.createUnmarshaller();
            return (DTOCustomer)marshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @param dtoApplication
     * @return
     */
    public static Questions fromXml(String xmlContent){
    	StringReader sr = new StringReader(xmlContent);
        try {
        	JAXBContext context = JAXB_QUESTIONS;
            Unmarshaller marshaller = context.createUnmarshaller();
            return (Questions)marshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @param xmlContent
     * @return
     */
    public static DTOApplication fromXml2DTOApplication(String xmlContent){
    	StringReader sr = new StringReader(xmlContent);
        try {
        	JAXBContext context = JAXB_DTO_APPLICATION;
            Unmarshaller marshaller = context.createUnmarshaller();
            return (DTOApplication)marshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @param xmlContent
     * @return
     */
    public static Application fromXml2Application(String xmlContent){
    	StringReader sr = new StringReader(xmlContent);
        try {
        	JAXBContext context = JAXB_APPLICATION;
            Unmarshaller marshaller = context.createUnmarshaller();
            return (Application)marshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @param xmlContent
     * @return
     */
    public static DTOCoverage fromXml2DTOCoverage(String xmlContent){
    	StringReader sr = new StringReader(xmlContent);
        try {
        	JAXBContext context = JAXB_DTO_COVERAGE;
            Unmarshaller marshaller = context.createUnmarshaller();
            return (DTOCoverage)marshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @param dtoClaim
     * @return
     */
    public static String toXml(DTOClaim dtoClaim){
    	StringWriter sw = new StringWriter();
        try {
            JAXBContext context = JAXB_DTO_CLAIM;
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.marshal(dtoClaim, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        //return xml
        return sw.toString();
    }
    
    /**
     * @param dtoApplication
     * @return
     */
    public static String toXml(DTOApplication dtoApplication){
    	return toXml(dtoApplication, null, 1);
    }
    
    /**
     * @param dtoApplication
     * @return
     */
    public static String toXml(DTOApplication dtoApplication, Integer transactionNumber, Integer policyVersion){
    	if(transactionNumber != null && transactionNumber > 0){
    		DTOBasicPolicy basicPolicy = dtoApplication.getDTOBasicPolicy();
    		basicPolicy.setTransactionNumber(new BigInteger(String.valueOf(transactionNumber)));
    	}
    	if(policyVersion != null && policyVersion > 0){
    		setPolicyVersion(dtoApplication, null, policyVersion);
    	}
    	
    	StringWriter sw = new StringWriter();
        try {
            JAXBContext context = JAXB_DTO_APPLICATION;
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.marshal(dtoApplication, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        //return xml
        return sw.toString();
    }
    
    
    /**
     * @param dtoCustomer
     * @return
     */
    public static String toXml(DTOCustomer dtoCustomer){
    	StringWriter sw = new StringWriter();
        try {
            JAXBContext context = JAXB_DTO_CUSTOMER;
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.marshal(dtoCustomer, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        //return xml
        return sw.toString();
    }
    
    /**
     * @param dtoApplication
     * @return
     */
    public static String toXml(DTOAccount dtoAccount){
    	StringWriter sw = new StringWriter();
        try {
            JAXBContext context = JAXB_DTO_ACCOUNT;
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.marshal(dtoAccount, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        //return xml
        return sw.toString();
    }
    
    /**
     * @param dtoApplication
     * @return
     */
    public static String toXml(DTOCoverage dtoCoverage){
    	StringWriter sw = new StringWriter();
        try {
            JAXBContext context = JAXB_DTO_COVERAGE;
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.marshal(dtoCoverage, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        //return xml
        return sw.toString();
    }
    
    /**
     * @param StopDt
     * @param StartDt
     * @param productVersion
     */
    public static void mapProductVersion(String StopDt, String StartDt, ProductVersion productVersion){
    	if(StopDt != null){
    		synchronized (routines.Utils.DATE_FRMT) {
    	    	try {
					productVersion.endDate = DATE_FRMT.parse(StopDt);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	if(StartDt != null){
    		synchronized (routines.Utils.DATE_FRMT) {
    	    	try {
					productVersion.startDate = DATE_FRMT.parse(StartDt);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    }
    

	public static boolean hasParsedData(Addr bean) {

		if (!StringUtils.isBlank(bean.getPreDirectional()))
			return true;
		if (!StringUtils.isBlank(bean.getPostDirectional()))
			return true;
		if (!StringUtils.isBlank(bean.getPrimaryNumber()))
			return true;
		if (!StringUtils.isBlank(bean.getSecondaryDesignator()))
			return true;
		if (!StringUtils.isBlank(bean.getSecondaryNumber()))
			return true;
		if (!StringUtils.isBlank(bean.getStreetName()))
			return true;
		if (!StringUtils.isBlank(bean.getSuffix()))
			return true;

		return false;

	}

	public static void convertParsedToUnparsed(Addr bean,
			boolean preserveParsedFields) {

		bean.setAddr1(getAddressLine1(bean));
		bean.setAddr2(getAddressLine2(bean));

		if (!preserveParsedFields) {
			clearParsedFields(bean);
		}

	}

	public static String getAddressLine1(Addr addr) {

		String address1 = "";

		address1 += !StringUtils.isBlank(addr.getPrimaryNumber()) ? (addr
				.getPrimaryNumber() + " ") : "";
		address1 += !StringUtils.isBlank(addr.getPreDirectional()) ? (addr
				.getPreDirectional() + " ") : "";
		address1 += !StringUtils.isBlank(addr.getStreetName()) ? (addr
				.getStreetName() + " ") : "";
		address1 += !StringUtils.isBlank(addr.getSuffix()) ? (addr.getSuffix() + " ")
				: "";
		address1 += !StringUtils.isBlank(addr.getPostDirectional()) ? (addr
				.getPostDirectional() + " ") : "";

		return address1.trim();

	}

	public static String getAddressLine2(Addr addr) {
		String address2 = "";

		address2 += (addr.getSecondaryDesignator() != null) ? (addr
				.getSecondaryDesignator() + " ") : "";
		address2 += (addr.getSecondaryNumber() != null) ? (addr
				.getSecondaryNumber() + " ") : "";

		return address2.trim();

	}

	public static void clearParsedFields(Addr address) {
		address.setPreDirectional("");
		address.setPostDirectional("");
		address.setPrimaryNumber("");
		address.setSecondaryDesignator("");
		address.setSecondaryNumber("");
		address.setStreetName("");
		address.setSuffix("");
	}

	public static String getAddressHash(Addr addr) {

		String hash = "";

		hash += (!StringUtils.isBlank(addr.getAddr1())) ? (addr.getAddr1() + " ")
				: "";
		hash += (!StringUtils.isBlank(addr.getAddr2())) ? (addr.getAddr2() + " ")
				: "";
		hash += (!StringUtils.isBlank(addr.getPrimaryNumber())) ? (addr
				.getPrimaryNumber() + " ") : "";
		hash += (!StringUtils.isBlank(addr.getPreDirectional())) ? (addr
				.getPreDirectional() + " ") : "";
		hash += (!StringUtils.isBlank(addr.getStreetName())) ? (addr
				.getStreetName() + " ") : "";
		hash += (!StringUtils.isBlank(addr.getSuffix())) ? (addr.getSuffix() + " ")
				: "";
		hash += (!StringUtils.isBlank(addr.getPostDirectional())) ? (addr
				.getPostDirectional() + " ") : "";
		hash += (!StringUtils.isBlank(addr.getSecondaryDesignator())) ? (addr
				.getSecondaryDesignator() + " ") : "";
		hash += (!StringUtils.isBlank(addr.getSecondaryNumber())) ? (addr
				.getSecondaryNumber() + " ") : "";

		hash += (!StringUtils.isBlank(addr.getCity())) ? (addr.getCity() + " ")
				: "";
		hash += (!StringUtils.isBlank(addr.getCounty())) ? (addr.getCounty() + " ")
				: "";
		hash += (!StringUtils.isBlank(addr.getStateProvCd())) ? (addr
				.getStateProvCd() + " ") : "";
		if (!StringUtils.isBlank(addr.getPostalCode())) {
			hash += addr.getPostalCode() + " ";
		}
		hash += (!StringUtils.isBlank(addr.getRegionCd())) ? (addr
				.getRegionCd() + " ") : "";

		StringBuffer cleanString = new StringBuffer(1024);
		StringBuffer str = new StringBuffer(hash);

		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetterOrDigit(str.charAt(i))
					|| Character.isSpaceChar(str.charAt(i))) {
				cleanString.append(Character.toUpperCase(str.charAt(i)));
			}
		}

		hash = cleanString.toString();

		// Replace all double spaces with a single space
		while (hash.indexOf("  ") > -1) {
			hash = hash.replaceAll("  ", " ");
		}

		// Trim
		hash = hash.trim();

		return hash;
	}

	public static Addr copyAddr(Addr addr){
		Addr copy = new Addr();
		copy.setAddr1(addr.getAddr1());
		copy.setAddr2(addr.getAddr2());
		copy.setAddrTypeCd(addr.getAddrTypeCd());
		copy.setAttention(addr.getAttention());
		copy.setCity(addr.getCity());
		copy.setCounty(addr.getCounty());
		copy.setDPV(addr.getDPV());
		copy.setDPVDesc(addr.getDPVDesc());
		copy.setDPVNotes(addr.getDPVNotes());
		copy.setDPVNotesDesc(addr.getDPVNotesDesc());
		copy.setId(addr.getId());
		copy.setLatitude(addr.getLatitude());
		copy.setLongitude(addr.getLongitude());
		copy.setPostalCode(addr.getPostalCode());
		copy.setPostDirectional(addr.getPostDirectional());
		copy.setPreDirectional(addr.getPreDirectional());
		copy.setPrimaryNumber(addr.getPrimaryNumber());
		copy.setPrimaryNumberSuffix(addr.getPrimaryNumberSuffix());
		copy.setRegionCd(addr.getRegionCd());
		copy.setScore(addr.getScore());
		copy.setSecondaryDesignator(addr.getSecondaryDesignator());
		copy.setSecondaryNumber(addr.getSecondaryNumber());
		copy.setStateProvCd(addr.getStateProvCd());
		copy.setStreetName(addr.getStreetName());
		copy.setSuffix(addr.getSuffix());
		
		return copy;
	}
	
	private static final QName SERVICE_NAME = new QName("http://www.gopetplan.com", "FimsService");
	
	/**
	 * @return
	 */
	public static FimsServiceSoap createFimsClient(String url){
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(FimsServiceSoap.class);
		//factory.setAddress("http://bb-helper.com:787/fims/fimsservice.asmx");
		factory.setAddress(url == null ? "http://webservices/fims/FimsService.asmx" : url);
		FimsServiceSoap client = (FimsServiceSoap) factory.create();
		return client;
	}
	
	/**
	 * @param dtoApp
	 */
	public static FetchQuoteResponse createQuote(generated.DTOApplication dtoApp, routines.LegacyPolicyData policyData, FimsServiceSoap client) throws Throwable{
		String typeCd = "UW";
		String controllingState = null;
		String carrierGroup = null;
		String carrier = null;
		String petName = null;
		String pettypeId = null;
		String breedId = null;
		String zip = null;
		String campaign = null;
		String origSourceId = "source";
		String payPlanType = null;
		
		boolean microchip = false;
		boolean vetstaff = false;
		boolean medserv = false;
		boolean prevph = false;
		boolean web = false;
		boolean workDog = false;
		XMLGregorianCalendar effectiveDate = null;
		XMLGregorianCalendar petdob = null;
		double deductible = 0D;
		double copayPct = 0D;
		int coinsurance = 0;
		
		//typeCd = dtoApp.getTypeCd();
		
		if(dtoApp.getDTOBasicPolicy() != null){
			try {
				effectiveDate = stringToXMLGregorianCalendar(dtoApp.getDTOBasicPolicy().getEffectiveDt());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatatypeConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			controllingState = dtoApp.getDTOBasicPolicy().getControllingStateCd();
			carrierGroup = dtoApp.getDTOBasicPolicy().getCarrierGroupCd();
			carrier= dtoApp.getDTOBasicPolicy().getCarrierCd();
			campaign = dtoApp.getDTOBasicPolicy().getCampaignCd();
			payPlanType = dtoApp.getDTOBasicPolicy().getPayPlanCd();
		}
		
		generated.DTORisk dtoRisk = dtoApp.getDTOLine().getDTORisk();
		if(dtoRisk != null){
			petName = dtoRisk.getPetName();
			pettypeId = dtoRisk.getPetType();
			breedId = dtoRisk.getBreed();
			try {
				petdob = stringToXMLGregorianCalendar(dtoRisk.getBirthDt());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatatypeConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			microchip = INDICATOR_YES.equalsIgnoreCase(dtoRisk.getMicrochipDiscInd());
			vetstaff = INDICATOR_YES.equalsIgnoreCase(dtoRisk.getVetStaffDiscInd());
			medserv = INDICATOR_YES.equalsIgnoreCase(dtoRisk.getMedicalServicesDiscInd());
			prevph = INDICATOR_YES.equalsIgnoreCase(dtoRisk.getPolicyHolderDiscountInd());
			if(dtoRisk.getDeductible() != null && dtoRisk.getDeductible().length() > 0){
				deductible = Double.parseDouble(dtoRisk.getDeductible());
			}
			if(dtoRisk.getCopayPct() != null && dtoRisk.getCopayPct().length() > 0){
				copayPct = Double.parseDouble(dtoRisk.getCopayPct());
			}
			web = INDICATOR_YES.equalsIgnoreCase(dtoRisk.getInternetDiscInd());
			workDog = INDICATOR_YES.equalsIgnoreCase(dtoRisk.getWorkingDogDiscInd());
		}
		generated.PartyInfo insuredPartyInfo = routines.Utils.getPartyInfo(dtoApp.getDTOInsured(), routines.Utils.PARTY_TYPE_CD_INSURED_PARTY);
		generated.Addr insuredMailingAddr = null;
		generated.Addr insuredBillingAddr = null;
		generated.EmailInfo insuredEmail = null;
		generated.PhoneInfo insuredPrimaryPhone = null;
		if(insuredPartyInfo != null){
			insuredMailingAddr = routines.Utils.getPartyAddr(insuredPartyInfo, routines.Utils.ADDR_TYPE_CODE_INSURED_LOOKUP_ADDR);
			insuredMailingAddr.setAddr1(insuredMailingAddr.getPrimaryNumber() + " " + insuredMailingAddr.getStreetName());
			insuredBillingAddr = insuredMailingAddr; //routines.Utils.getPartyAddr(insuredPartyInfo, routines.Utils.ADDR_TYPE_CODE_INSURED_BILLING_ADDR);
			
			insuredEmail = getPartyEmail(insuredPartyInfo, EMAIL_TYPE_CODE_INSURED_EMAIL);
			insuredPrimaryPhone = getPartyPhone(insuredPartyInfo, PHONE_TYPE_CODE_INSURED_PHONE_PRIMARY);
			zip = insuredMailingAddr.getPostalCode();
		}
		
		//System.out.println("Executing checkEmailExists()");
		//boolean emailExists = client.checkEmailExists("mfedorov@netflexity.com");
		//System.out.println("Executed checkEmailExists(): " + emailExists);
		
		System.out.println("Executing createQuote()");
		FetchQuoteResponse response = client.createQuote(typeCd, effectiveDate, controllingState, carrierGroup, petName, pettypeId, breedId, petdob, zip, microchip, vetstaff, medserv, prevph, campaign, web, deductible, copayPct);
		System.out.println("AppId#:" + response.getApplicationId());
		
		/*
		First a CreateQuote is called.
		Next we create client using SaveClient. SaveClient itself calls CheckEmailExists, don't worry about it
		Next we save quote to created client using SaveQuoteExistingPHFull.
		Next we update quote with questions and answers - UpdateQuote.
		And final step - IssuePolicy
		 */
		PolicyType policyType = PolicyType.GOLD;
		String policyForm = dtoApp.getDTOBasicPolicy().getPolicyForm();
		if ("Silver".equalsIgnoreCase(policyForm)){
			policyType = PolicyType.SILVER;
		}
		else if ("Bronze".equalsIgnoreCase(policyForm)){
			policyType = PolicyType.BRONZE;
		}
		else if ("Introductory".equalsIgnoreCase(policyForm)){
			policyType = PolicyType.INTRODUCTORY;
		}
		
		QuestionReplies qReplies = dtoApp.getQuestionReplies();
		String gender = null;
		String petPrice = "1";
		String sourceOfPet = null;
		boolean treatment = false;
		boolean examined = false;
		XMLGregorianCalendar lastCheckup = null;
		QuestionReply qr = getQuestionReply(qReplies, "Gender");
		if(qr != null){
			gender = qr.getValue();
		}
		qr = getQuestionReply(qReplies, "Price");
		if(qr != null){
			petPrice = qr.getValue();
		}
		qr = getQuestionReply(qReplies, "Price");
		if(qr != null){
			petPrice = qr.getValue();
		}
		qr = getQuestionReply(qReplies, "LocationOrigin");
		if(qr != null){
			sourceOfPet = qr.getValue();
		}
		qr = getQuestionReply(qReplies, "PreviousIllness");
		if(qr != null){
			treatment = INDICATOR_YES.equalsIgnoreCase(qr.getValue());
		}
		qr = getQuestionReply(qReplies, "LastExam");
		if(qr != null){
			String val = qr.getValue();
			if(StringUtils.isNotBlank(val)){
				try {
					lastCheckup = stringToXMLGregorianCalendar(val);
					examined = true;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DatatypeConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		System.out.println("Executing saveClient()");
		String ipid = client.saveClient(insuredPartyInfo.getNameInfo().getSuffixCd(), insuredPartyInfo.getNameInfo().getGivenName(), insuredPartyInfo.getNameInfo().getSurname(), insuredEmail.getEmailAddr(), policyData.getPASSWORD(), insuredMailingAddr.getAddr1(), insuredMailingAddr.getAddr2(), insuredMailingAddr.getCity(), insuredMailingAddr.getStateProvCd(), zip, insuredPrimaryPhone.getPhoneNumber());
		if(ipid != null){
			if(ipid.startsWith("new client REGISTERED with IPID:")){
				ipid = ipid.substring("new client REGISTERED with IPID:".length());
			}
			else if(ipid.startsWith("This email is already registered to user with IPID: ")){
				ipid = ipid.substring("This email is already registered to user with IPID: ".length());
			}
		}
		System.out.println("Customer ID:" + ipid);
		
		System.out.println("Executing saveQuoteExistingPHFull()");
		String quoteId1 = client.saveQuoteExistingPHFull(ipid, carrier, controllingState, petdob, breedId, pettypeId, zip, campaign, origSourceId, microchip, vetstaff, medserv, prevph, web, workDog, ((int)deductible), copayPct, coinsurance, response.getApplicationId(), insuredBillingAddr.getAddr1(), insuredBillingAddr.getAddr2(), insuredBillingAddr.getCity(), insuredBillingAddr.getPostalCode(), insuredBillingAddr.getStateProvCd(), effectiveDate, petName, "admin", policyType);
		System.out.println("Quote#:" + quoteId1);
		
		System.out.println("Executing updateQuote()");
		String quoteId2 = client.updateQuote(response.getApplicationId(), gender, false, petPrice, sourceOfPet, examined, lastCheckup, treatment);
		System.out.println("Quote#:" + quoteId2);
		
		//<ElectronicPaymentSource id="BasicPolicy-ALZ5051589-ePaymentSource-Credit Card" CreditCardNumber="4111111111111111" CustomerPaymentProfileId="21553350" CustomerProfileId="23581560" MethodCd="Credit Card" SourceTypeCd="InstallmentSource"/>
		
		IssuePolicy issuePolicyParams = new IssuePolicy();
		issuePolicyParams.setApplicationId(response.getApplicationId());
		issuePolicyParams.setCoverageStart(effectiveDate);
		issuePolicyParams.setIpid(ipid);
		issuePolicyParams.setPeriod("1");//dtoApp.getDTOBasicPolicy().getRenewalTermCd()); // 1 Year
		//ElectronicPaymentSource eps = dtoApp.getDTOBasicPolicy().getElectronicPaymentSource();
		//if(eps != null){
			issuePolicyParams.setCardNumber("4111111111111111"); //eps.getCreditCardNumber());
			issuePolicyParams.setCardholderName(insuredPartyInfo.getNameInfo().getGivenName() + " " + insuredPartyInfo.getNameInfo().getSurname());
			issuePolicyParams.setCvv("111");
			issuePolicyParams.setExpDate("201601");
			issuePolicyParams.setItsACreditCard(true);
		//}
		issuePolicyParams.setBankAccountNumber("xxx");
		issuePolicyParams.setBankAccountTypeCd("xxx");
		issuePolicyParams.setBankName("xxx");
		issuePolicyParams.setRoutingNumber("xxx");
		
		System.out.println("Executing issuePolicy()");
		IssuePolicyResponse issuePolicyResponse = client.issuePolicy(issuePolicyParams);
		if(issuePolicyResponse != null && issuePolicyResponse.getIssuePolicyResult() != null){
			System.out.println("Policy Issued:" + issuePolicyResponse.getIssuePolicyResult().getId());
		}
		System.out.println("Policy Issued for " + dtoApp.getDTOBasicPolicy().getPolicyDisplayNumber());
		/*
		FetchQuoteResponse response = null;
		try {
			URL wsdl = new URL("http://webservices/fims/FimsService.asmx?WSDL");
			System.out.println("URL Resolved");
			com.gopetplan.FimsService ss = new com.gopetplan.FimsService(wsdl, SERVICE_NAME);
			System.out.println("Got Service");
			com.gopetplan.FimsServiceSoap port = ss.getFimsServiceSoap(); 
			System.out.println("Got Port");
			
			boolean emailExists = port.checkEmailExists("jopa@jopa.com");
			System.out.println("Executed checkEmailExists(): " + emailExists);
			
			response = port.createQuote(typeCd, effectiveDate, controllingState, carrierGroup, petName, pettypeId, breedId, petdob, zip, microchip, vetstaff, medserv, prevph, campaign, web, deductible, copayPct);
			System.out.println("Quote#:" + response.getQuoteNumber());
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		*/
		
		return response;
	}
	
	/**
	 * @param s
	 * @return
	 * @throws java.text.ParseException
	 * @throws javax.xml.datatype.DatatypeConfigurationException
	 */
	public static javax.xml.datatype.XMLGregorianCalendar stringToXMLGregorianCalendar(String s) throws java.text.ParseException, javax.xml.datatype.DatatypeConfigurationException{
		 javax.xml.datatype.XMLGregorianCalendar result = null;
		 Date date;
		 java.text.SimpleDateFormat simpleDateFormat;
		 java.util.GregorianCalendar gregorianCalendar;
		 
		 simpleDateFormat = new java.text.SimpleDateFormat("yyyyMMdd"); //"yyyy-MM-dd'T'HH:mm:ss");
		 date = simpleDateFormat.parse(s);        
		 gregorianCalendar = (GregorianCalendar)GregorianCalendar.getInstance();
		 gregorianCalendar.setTime(date);
		 result =  javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
		 return result;
	}
	
	/**
	 * @param globalMap
	 * @param baseFilePath
	 * @param activeOnlyPolicies
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public static void processTransactions(Map<String, Object> globalMap, String baseFilePath, boolean activeOnlyPolicies) throws IOException{
		  List<List<generated.DTOApplication>> all_partitions = (List<List<generated.DTOApplication>>) globalMap.get("partitions");
		  Map<String, List<generated.DTOAccount>> accountByPolicyNumber = (Map<String, List<generated.DTOAccount>>) globalMap.get("accountByPolicyNumber");
		  Map<String, String> existingPolicyNumbers = (Map<String, String>) globalMap.get("existingPolicyNumbers");
		  Map<String, routines.LegacyPolicyData> policyDataByPolicyNumber = (Map<String, routines.LegacyPolicyData>) globalMap.get("policyDataByPolicyNumber");
		  Map<String, List<routines.LegacyCoverage>> coverageByPolicy = (Map<String, List<routines.LegacyCoverage>>) globalMap.get("coverageByPolicy");
		  Map<String, java.math.BigDecimal> taxesByPolicyNumbers = (Map<String, java.math.BigDecimal>) globalMap.get("taxesByPolicyNumbers");
		  Map<String, routines.LegacyCancelationNotice> cancelNoticesByPolicyNumber = (Map<String, routines.LegacyCancelationNotice>) globalMap.get("cancelNoticesByPolicyNumber");
		  Map<String, List<routines.LegacyPolicyNote>> policyNotesByPolicyNumber = (Map<String, List<routines.LegacyPolicyNote>>) globalMap.get("policyNotesByPolicyNumber");
		  Map<String, List<routines.LegacyEndosement>> endorsementsByPolicyNumber = (Map<String, List<routines.LegacyEndosement>>) globalMap.get("endorsementsByPolicyNumber");
		  Map<Integer, List<routines.LegacyCoverage>> coverageBySchema = (Map<Integer, List<routines.LegacyCoverage>>) globalMap.get("coverageBySchema");
		  Map<String, routines.LegacyPolicyExpiration> expirationsByPolicyNumber = (Map<String, routines.LegacyPolicyExpiration>) globalMap.get("expirationsByPolicyNumber");
		  //Map<String, routines.LegacyPolicyHold> holdsByPolicyNumber = (Map<String, routines.LegacyPolicyHold>) globalMap.get("holdsByPolicyNumber");
		  Map<String, List<routines.LegacyRewrite>> rewritesByPolicyNumber = (Map<String, List<routines.LegacyRewrite>>) globalMap.get("rewritesByPolicyNumber");
		  
		  Integer currentIteration = ((Integer)globalMap.get("tLoop_1_CURRENT_ITERATION"));
		  
		  List<generated.DTOApplication> current_partition = all_partitions.get(currentIteration -1);

		  StringBuilder builder = new StringBuilder(1024 * 1024);
		  builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		  builder.append("<DTORoot>");

		  String transactionGroupNumber = null;
		  int appIndex = 0;
		  int transactions = 0;
		  int transactionNumber = 1;
		  int policyVersion = 1;
		  List<routines.PolicyXmlContainer> transactionPolicies = new ArrayList<PolicyXmlContainer>();
		  for (generated.DTOApplication dtoApplication : current_partition){
		  	boolean activePolicy = routines.Utils.POLICY_RECORD_STATUS_POLICY.equalsIgnoreCase(dtoApplication.getDTOBasicPolicy().getTransactionStatus());
		  	
		  	// Reset id to null. Used to store POLICY_DETAILS_ID
		  	dtoApplication.setId(null);
		  	dtoApplication.getDTOBasicPolicy().setTransactionStatus(null);
		  	
		  	if(existingPolicyNumbers.get(dtoApplication.getDTOBasicPolicy().getPolicyNumber()) != null){
		  		System.out.println("Skipping " + dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
		  		continue;
		  	}
		  	
		  	//System.out.println("PN#:" + dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
		  	if(transactionGroupNumber == null || !transactionGroupNumber.equals(dtoApplication.getExternalStateData())){ //dtoApplication.getDTOBasicPolicy().getPolicyNumber())){
		  		if(transactionGroupNumber != null){
		  			writePolicies(transactionPolicies, builder);
		  			builder.append("</Transactional>");
		  		}
		  		transactionPolicies.clear();
		  		builder.append("<Transactional>");
		  		transactions++;
		  		transactionNumber = 1;
		  		policyVersion = 1;
		  	}
		  	
		  	transactionGroupNumber = dtoApplication.getExternalStateData();//getDTOBasicPolicy().getPolicyNumber();
		  	dtoApplication.setExternalStateData(null);
		  	
		  	routines.Utils.validatePrimaryTelephone(dtoApplication);
		  	
		  	routines.LegacyPolicyData policyData_curr = policyDataByPolicyNumber.get(dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
		  	DTOTransactionInfo dtoTransactionInfo = dtoApplication.getDTOTransactionInfo();
		  	
		  	// NOTE: Perform coverage mapping.
		  	List<routines.LegacyCoverage> planCovarages = coverageByPolicy.get(dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
		  	if(planCovarages != null && !planCovarages.isEmpty()){
		  		dtoApplication.setExternalStateData(policyData_curr.getTRANSACTION_GROUP().toString());
		  		
		  		DTOApplication dtoApplication_prev = (appIndex == 0 ? null : current_partition.get(appIndex-1));
		  		if(dtoApplication_prev != null){
		  			routines.LegacyPolicyData policyData_prev = policyDataByPolicyNumber.get(dtoApplication_prev.getDTOBasicPolicy().getPolicyDisplayNumber());
		  			dtoApplication_prev.setExternalStateData(policyData_prev.getTRANSACTION_GROUP().toString());
		  		}
		  		
				routines.Utils.mapCoverage(dtoApplication, dtoApplication_prev, planCovarages, policyData_curr);
				
				// Reset.
				dtoApplication.setExternalStateData(null);
				if(dtoApplication_prev != null){
					dtoApplication_prev.setExternalStateData(null);
				}
		  	}
		  	else{
		  		System.err.println("XXX Failed to obtain Coverage data for policy:" + dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
		  	}
		  	
		  	// Store cancellation, renewal and newBusiness flags since dtoApplication will be reset.
		  	String transactionCd = dtoApplication.getDTOBasicPolicy().getTransactionCd();
		  	boolean renewalPolicy = TRANS_CODE_RENEWAL.equalsIgnoreCase(transactionCd);
		  	boolean newBusinessPolicy = TRANS_CODE_NEW_BUSINESS.equalsIgnoreCase(transactionCd);
		  	boolean cancellation = routines.Utils.TRANS_CODE_CANCELLATION.equalsIgnoreCase(dtoTransactionInfo.getTransactionCd());
		  	boolean rewriteRenewalPolicy = false;
		  	boolean endorsed = false;
		  	
		  	if(cancellation){
		  		routines.Utils.setCancellationPremiums(dtoApplication);
		  	}
		  	
		  	List<LegacyPolicyNote> legacyPolicyNotes = policyNotesByPolicyNumber.get(dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
		  	if(legacyPolicyNotes != null && !legacyPolicyNotes.isEmpty()){
	  			applyPolicyNote(dtoApplication, legacyPolicyNotes);
	  		}
		  	
		  	// This term has been rewritten and hence the full pay has to be used.
		  	List<routines.LegacyRewrite> rewrites = rewritesByPolicyNumber.get(dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
	    	if(rewrites != null && !rewrites.isEmpty()){
	    		rewriteRenewalPolicy = true;
			}
	    	
		  	// Only process endorsement transactions.
	    	List<routines.PolicyXmlContainer> endorsementTransactions = new ArrayList<PolicyXmlContainer>();
	    	if(hasAppliedEndorsements(policyData_curr, coverageBySchema, dtoApplication, endorsementsByPolicyNumber)){
		  		System.out.println("Applied endorsements for policy:" + dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
		  		endorsed = true;
		  	}
		  	
		  	if(cancellation){
		  		transactionNumber = 2;
		  		List<routines.LegacyEndosement> endorsements = endorsementsByPolicyNumber.get(dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
			  	if(endorsements != null && !endorsements.isEmpty()){
			  		transactionNumber += endorsements.size();
			  	}
		  	}
		  	
		  	if(renewalPolicy || newBusinessPolicy){
		  		transactionNumber = 1;
		  		if(renewalPolicy){
		  			policyVersion++;
		  		}
		  	}
		  	
		  	//System.out.println(dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber() + ": DTOTransactionInfo.TransactionCd:" + dtoTransactionInfo.getTransactionCd() + ", newBusiness:" + newBusinessPolicy + ", rewrite:" + rewriteRenewalPolicy + ", cancelation:" + cancellation + ", endorsed:" + endorsed  + ", renewal:" + renewalPolicy + ", transNumber:" + transactionNumber);
		  	
		  	String appStr = routines.Utils.toXml(dtoApplication, transactionNumber++, policyVersion);
		  	routines.PolicyXmlContainer appStrContainer = new routines.PolicyXmlContainer();
		  	appStrContainer.setContent(appStr);
		  	appStrContainer.setTransactionNumber(transactionNumber - 1);
		  	appStrContainer.setEffectiveDt(dtoApplication.getDTOBasicPolicy().getEffectiveDt());
	  		
		  	// Where do we write accounts to?
		  	routines.PolicyXmlContainer accountsContainer = appStrContainer;
		  		
		  	// Handle canceled policies.
		  	if(cancellation){
		  		routines.LegacyPolicyData policyData_prev = null;
		  		DTOApplication dtoApplication_prev = (appIndex == 0 ? null : current_partition.get(appIndex-1));
		  		
		  		dtoApplication.setExternalStateData(policyData_curr.getTRANSACTION_GROUP().toString());
		  		
		  		if(dtoApplication_prev != null){
		  			policyData_prev = policyDataByPolicyNumber.get(dtoApplication_prev.getDTOBasicPolicy().getPolicyDisplayNumber());
		  			dtoApplication_prev.setExternalStateData(policyData_prev.getTRANSACTION_GROUP().toString());
		  		}
		  		
		  		transactionNumber = 1;
		  		int policyVersionCopy = policyVersion;
		  		if(policyVersionCopy > 1){
		  			policyVersionCopy--;
		  		}
		  		
		  		if(rewriteRenewalPolicy){
		  			applyRewriteRenewals(dtoApplication, dtoApplication_prev, rewrites);
		  		}
		  		else{
		  			routines.Utils.convertCanceledToRenewedOrNew(taxesByPolicyNumbers, policyData_curr, policyData_prev, dtoApplication, dtoApplication_prev, activeOnlyPolicies);
		  		}
		  		dtoApplication.setExternalStateData(null);
		  		
		  		//builder.append(routines.Utils.toXml(dtoApplication, transactionNumber++));
		  		routines.PolicyXmlContainer container = new routines.PolicyXmlContainer();
		  		container.setContent(routines.Utils.toXml(dtoApplication, transactionNumber++, policyVersionCopy));
		  		container.setTransactionNumber(transactionNumber - 1);
		  		container.setEffectiveDt(dtoApplication.getDTOBasicPolicy().getEffectiveDt());
		  		transactionPolicies.add(container);
		  		accountsContainer = container;
		  		
		  		transactionNumber++;
		  	}
		  	else if(rewriteRenewalPolicy){
		  		routines.LegacyPolicyData policyData_prev = null;
		  		DTOApplication dtoApplication_prev = (appIndex == 0 ? null : current_partition.get(appIndex-1));
		  		
		  		dtoApplication.setExternalStateData(policyData_curr.getTRANSACTION_GROUP().toString());
		  		
		  		if(dtoApplication_prev != null){
		  			policyData_prev = policyDataByPolicyNumber.get(dtoApplication_prev.getDTOBasicPolicy().getPolicyDisplayNumber());
		  			dtoApplication_prev.setExternalStateData(policyData_prev.getTRANSACTION_GROUP().toString());
		  		}
		  		
		  		transactionNumber = 1;
		  		int policyVersionCopy = policyVersion;
		  		if(policyVersionCopy > 1){
		  			policyVersionCopy--;
		  		}
		  		
	  			applyRewriteRenewals(dtoApplication, dtoApplication_prev, rewrites);
	  			
	  			dtoApplication.setExternalStateData(null);
		  		
		  		//builder.append(routines.Utils.toXml(dtoApplication, transactionNumber++));
		  		routines.PolicyXmlContainer container = new routines.PolicyXmlContainer();
		  		container.setContent(routines.Utils.toXml(dtoApplication, transactionNumber++, policyVersionCopy));
		  		container.setTransactionNumber(transactionNumber - 1);
		  		container.setEffectiveDt(dtoApplication.getDTOBasicPolicy().getEffectiveDt());
		  		transactionPolicies.add(container);
		  		accountsContainer = container;
		  		
		  		transactionNumber++;
	  		}
		  	else{
		  		//builder.append(appStr);
		  		transactionPolicies.add(appStrContainer);
		  		accountsContainer = appStrContainer;
		  	}
		  	
		  	// Get cancellation notice for this policy.
		  	LegacyCancelationNotice cancelNotice = cancelNoticesByPolicyNumber.get(dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
		  	// Get hold for this policy.
		  	//LegacyPolicyHold policyHold = holdsByPolicyNumber == null ? null : holdsByPolicyNumber.get(dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
		  	
		  	// Process accounts.
		  	List<DTOAccount> dtoAccounts = accountByPolicyNumber.get(dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
		  	if(dtoAccounts != null){
		  		routines.Utils.processItemAdjustment(globalMap, dtoApplication, dtoAccounts, policyData_curr.INCLUDE_WRITEOFF);
		  		if(!dtoAccounts.isEmpty()){
		  			double amount = 0D;
		  			for(int j = 0; j < dtoAccounts.size(); j++){
		  				DTOAccount dtoAccount = dtoAccounts.get(j);
		  	
	  					String accStr = routines.Utils.toXml(dtoAccount);
	  					if(endorsed){
	  						routines.PolicyXmlContainer container = new routines.PolicyXmlContainer();
	  				  		container.setContent(accStr);
	  				  		if(dtoAccount.getDTOARTrans() != null && !dtoAccount.getDTOARTrans().isEmpty()){
	  				  			container.setEffectiveDt(dtoAccount.getDTOARTrans().get(0).getTransactionDt());
	  				  		}
	  				  		endorsementTransactions.add(container);
	  					}
	  					else{
	  						accountsContainer.getAccountsContents().add(accStr);
	  					}
		  			}
		  		}
		  	}
		  	
		  	// Render cancellation or cancellation notice application.
		  	if(endorsed){
		  		//System.out.println("Processed endorsements for policy:" + dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
		  		
		  		// If cancellation, means the transaction for cancellation or rewrite-renewal has already been generated.
		  		if(cancellation || rewriteRenewalPolicy){
		  			transactionNumber--;
		  		}
		  		
		  		List<routines.LegacyEndosement> endorsements = endorsementsByPolicyNumber.get(dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
			  	if(endorsements != null && !endorsements.isEmpty()){
			  		for (LegacyEndosement legacyEndosement : endorsements) {
				  		mapAppEndorsement(dtoApplication, legacyEndosement, transactionNumber, coverageBySchema, policyData_curr);
				  	
				  		//builder.append(routines.Utils.toXml(dtoApplication, transactionNumber++));
				  		routines.PolicyXmlContainer container = new routines.PolicyXmlContainer();
				  		container.setContent(routines.Utils.toXml(dtoApplication, transactionNumber++, policyVersion));
				  		container.setTransactionNumber(transactionNumber - 1);
				  		container.setEffectiveDt(dtoApplication.getDTOTransactionInfo().getBookDt());
				  		endorsementTransactions.add(container);
			  		}
			  	}
			  	
		  		// Sort all endorsement transactions, both DTOAccounts and DTOApplication.
		  		Collections.sort(endorsementTransactions, PolicyXmlContainer.EFFECTIVE_DATE_ORDER);
				for (PolicyXmlContainer policyXmlContainer : endorsementTransactions) {
					transactionPolicies.add(policyXmlContainer);
				}
		  	}
		  	else if(cancelNotice != null){
		  		convertToCancelNotice(dtoApplication, cancelNotice);
		  		
		  		//builder.append(routines.Utils.toXml(dtoApplication, transactionNumber++));
		  		routines.PolicyXmlContainer container = new routines.PolicyXmlContainer();
		  		container.setContent(routines.Utils.toXml(dtoApplication, transactionNumber++, policyVersion));
		  		container.setTransactionNumber(transactionNumber - 1);
		  		container.setEffectiveDt(dtoApplication.getDTOBasicPolicy().getEffectiveDt());
		  		transactionPolicies.add(container);
		  	}
		  	
		  	if(cancellation){
	  			//builder.append(appStr);
		  		if(endorsed){
		  			System.out.println("Cancellation transNumber:" + transactionNumber);
		  			appStrContainer.setTransactionNumber(transactionNumber);
		  		}
	  			transactionPolicies.add(appStrContainer);
	  		}
		  	/*
		  	else if(!cancellation && rewriteRenewalPolicy){
		  		DTOApplication dtoApplication_prev = (appIndex == 0 ? null : current_partition.get(appIndex-1));
	  			applyRewriteRenewals(dtoApplication, dtoApplication_prev, rewrites);
	  			dtoApplication.setExternalStateData(null);
	  			
	  			routines.PolicyXmlContainer container = new routines.PolicyXmlContainer();
		  		container.setContent(routines.Utils.toXml(dtoApplication, transactionNumber++, policyVersion));
		  		container.setTransactionNumber(transactionNumber - 1);
		  		container.setEffectiveDt(dtoApplication.getDTOBasicPolicy().getEffectiveDt());
		  		transactionPolicies.add(container);
	  		}
		  	*/
	  		/*else if(policyHold != null){
		  		convertToHold(dtoApplication, policyHold);
		  		
		  		//builder.append(routines.Utils.toXml(dtoApplication, transactionNumber++));
		  		routines.PolicyXmlContainer container = new routines.PolicyXmlContainer();
		  		container.setContent(routines.Utils.toXml(dtoApplication, transactionNumber++));
		  		container.setTransactionNumber(transactionNumber - 1);
		  		container.setEffectiveDt(dtoApplication.getDTOBasicPolicy().getEffectiveDt());
		  		transactionPolicies.add(container);
		  	}*/
		  	
		  	// Determine if this is the last policy within transaction group.
		  	DTOApplication dtoApplication_next = (appIndex >= (current_partition.size() - 1) ? dtoApplication : current_partition.get(appIndex+1));
		  	if(!dtoApplication_next.getDTOBasicPolicy().getId().equalsIgnoreCase(dtoApplication.getDTOBasicPolicy().getId())
		  			|| (appIndex == (current_partition.size() - 1))){
		  		// Determine if this transaction group has expiration policy record.
		  		routines.LegacyPolicyExpiration exp = expirationsByPolicyNumber.get(dtoApplication.getDTOBasicPolicy().getId());
		  		if(exp != null){
		  			convertToExpiration(dtoApplication, exp);
		  			
		  			//builder.append(routines.Utils.toXml(dtoApplication, transactionNumber++));
		  			routines.PolicyXmlContainer container = new routines.PolicyXmlContainer();
			  		container.setContent(routines.Utils.toXml(dtoApplication, transactionNumber++, policyVersion));
			  		container.setTransactionNumber(transactionNumber - 1);
			  		container.setEffectiveDt(dtoApplication.getDTOBasicPolicy().getEffectiveDt());
			  		transactionPolicies.add(container);
		  		}
		  	}
		  	
		  	//builder.append("</Transactional>");
		  	appIndex++;
		  }
		  writePolicies(transactionPolicies, builder);
		  
		  builder.append("</Transactional>");
		  builder.append("</DTORoot>");
		  
		  String fileName = baseFilePath + "/trans_" + currentIteration + ".xml";
		  System.out.println("File " + fileName + " contains " + transactions + " transactions with total of " + transactionPolicies.size() + " transactions generated.");
		  routines.FileUtils.saveTextFileContent(new java.io.File(fileName), builder.toString());
	}

	/**
	 * @param transactionPolicies
	 * @param builder
	 */
	protected static void writePolicies(List<routines.PolicyXmlContainer> transactionPolicies, StringBuilder builder){
		// Collections.sort(transactionPolicies, PolicyXmlContainer.EFFECTIVE_DATE_ORDER);
		for (PolicyXmlContainer policyXmlContainer : transactionPolicies) {
			builder.append(policyXmlContainer.getContent());
			
			// Add DTO Accounts.
			List<String> accountContents = policyXmlContainer.getAccountsContents();
			for (String accountsContent : accountContents) {
				builder.append(accountsContent);
			}
		}
	}
	
	/**
	 * @param globalMap
	 * @param baseFilePath
	 * @param activeOnlyPolicies
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public static void processEndorsementTransactions(Map<String, Object> globalMap, String baseFilePath, boolean activeOnlyPolicies) throws IOException{
		  List<List<generated.DTOApplication>> all_partitions = (List<List<generated.DTOApplication>>) globalMap.get("partitions");
		  Map<String, List<generated.DTOAccount>> accountByPolicyNumber = (Map<String, List<generated.DTOAccount>>) globalMap.get("accountByPolicyNumber");
		  Map<String, String> existingPolicyNumbers = (Map<String, String>) globalMap.get("existingPolicyNumbers");
		  Map<String, routines.LegacyPolicyData> policyDataByPolicyNumber = (Map<String, routines.LegacyPolicyData>) globalMap.get("policyDataByPolicyNumber");
		  Map<Integer, List<routines.LegacyCoverage>> coverageBySchema = (Map<Integer, List<routines.LegacyCoverage>>) globalMap.get("coverageBySchema");
		  
		  Integer currentIteration = ((Integer)globalMap.get("tLoop_1_CURRENT_ITERATION"));
		  
		  List<generated.DTOApplication> current_partition = all_partitions.get(currentIteration -1);

		  StringBuilder builder = new StringBuilder(1024 * 1024);
		  builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		  builder.append("<DTORoot>");

		  String policyNumber = null;
		  int appIndex = 0;
		  for (generated.DTOApplication dtoApplication : current_partition){
		  	
		  	// Reset id to null. Used to store POLICY_DETAILS_ID
		  	dtoApplication.setId(null);
		  	dtoApplication.getDTOBasicPolicy().setTransactionStatus(null);
		  	
		  	//System.out.println("PN#:" + dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
		  	if(policyNumber == null || !policyNumber.equals(dtoApplication.getExternalStateData())){ //dtoApplication.getDTOBasicPolicy().getPolicyNumber())){
		  		if(policyNumber != null){
		  			builder.append("</Transactional>");
		  		}
		  		builder.append("<Transactional>");
		  	}
		  	policyNumber = dtoApplication.getExternalStateData();//getDTOBasicPolicy().getPolicyNumber();
		  	dtoApplication.setExternalStateData(null);
		  	
		  	routines.Utils.validatePrimaryTelephone(dtoApplication);
		  	
		  	routines.LegacyPolicyData policyData_curr = policyDataByPolicyNumber.get(dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
		  	DTOTransactionInfo dtoTransactionInfo = dtoApplication.getDTOTransactionInfo();
		  	
		  	// Only process endorsement transactions.
		  	Map<String, List<routines.LegacyEndosement>> endorsementsByPolicyNumber = (Map<String, List<routines.LegacyEndosement>>) globalMap.get("endorsementsByPolicyNumber");
		  	List<routines.LegacyEndosement> endorsements = endorsementsByPolicyNumber.get(dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
		  	if(endorsements != null && !endorsements.isEmpty()){
		  		int i = 1;
		  		for (LegacyEndosement legacyEndosement : endorsements) {
		  			mapAppEndorsement(dtoApplication, legacyEndosement, i++, coverageBySchema, policyData_curr);
			  		builder.append(routines.Utils.toXml(dtoApplication));
			  		
				  	//builder.append("</Transactional>");
				  	appIndex++;
				}
		  	}
		  }
		  builder.append("</Transactional>");
		  builder.append("</DTORoot>");
		  
		  routines.FileUtils.saveTextFileContent(new java.io.File(baseFilePath + "/endorse_trans_" + currentIteration + ".xml"), builder.toString());
	}
	
	/**
	 * @param policyData_curr
	 * @param coverageBySchema
	 * @param dtoApplication
	 * @param endorsementsByPolicyNumber
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasAppliedEndorsements(routines.LegacyPolicyData policyData_curr, Map<Integer, List<routines.LegacyCoverage>> coverageBySchema, generated.DTOApplication dtoApplication, Map<String, List<routines.LegacyEndosement>> endorsementsByPolicyNumber) throws IOException{
	  	// Only process endorsement transactions.
	  	List<routines.LegacyEndosement> endorsements = endorsementsByPolicyNumber.get(dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
	  	if(endorsements != null && !endorsements.isEmpty()){
	  		List<routines.LegacyEndosement> endorsementsReversed = new ArrayList<LegacyEndosement>(endorsements);
	  		Collections.reverse(endorsementsReversed);
	  		for (LegacyEndosement legacyEndosement : endorsementsReversed) {
	  			
	  			// Get last endorsement.
	  			//LegacyEndosement legacyEndosement = endorsements.get(endorsements.size()-1);
	  			applyEnsorsementData(dtoApplication, legacyEndosement, coverageBySchema, policyData_curr);
	  		}
	  		return true;
	  	}
	  	return false;
	}
	
	/**
	 * @param globalMap
	 * @param baseFilePath
	 * @param activeOnlyPolicies
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasEndorsement(routines.LegacyPolicyData policyData_curr, Map<Integer, List<routines.LegacyCoverage>> coverageBySchema, generated.DTOApplication dtoApplication, Map<String, List<routines.LegacyEndosement>> endorsementsByPolicyNumber) throws IOException{
	  	// Only process endorsement transactions.
	  	List<routines.LegacyEndosement> endorsements = endorsementsByPolicyNumber.get(dtoApplication.getDTOBasicPolicy().getPolicyDisplayNumber());
	  	if(endorsements != null && !endorsements.isEmpty()){
	  		return true;
	  	}
	  	return false;
	}
}