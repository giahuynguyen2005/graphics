/*
 * @(#)Function.java        5.2.0    2023-01-29
 *
 * MathParser.org-mXparser DUAL LICENSE AGREEMENT as of date 2023-01-29
 * The most up-to-date license is available at the below link:
 * - https://mathparser.org/mxparser-license
 *
 * AUTHOR: Copyright 2010 - 2023 Mariusz Gromada - All rights reserved
 * PUBLISHER: INFIMA - https://payhip.com/infima
 *
 * SOFTWARE means source code and/or binary form and/or documentation.
 * PRODUCT: MathParser.org-mXparser SOFTWARE
 * LICENSE: DUAL LICENSE AGREEMENT
 *
 * BY INSTALLING, COPYING, OR OTHERWISE USING THE PRODUCT, YOU AGREE TO BE
 * BOUND BY ALL OF THE TERMS AND CONDITIONS OF THE DUAL LICENSE AGREEMENT.
 *
 * AUTHOR & PUBLISHER provide the PRODUCT under the DUAL LICENSE AGREEMENT
 * model designed to meet the needs of both non-commercial use as well as
 * commercial use.
 *
 * NON-COMMERCIAL USE means any use or activity where a fee is not charged
 * and the purpose is not the sale of a good or service, and the use or
 * activity is not intended to produce a profit. NON-COMMERCIAL USE examples:
 *
 * 1. Free Open-Source Software ("FOSS").
 * 2. Non-commercial use in research, scholarly and education.
 *
 * COMMERCIAL USE means any use or activity where a fee is charged or the
 * purpose is the sale of a good or service, or the use or activity is
 * intended to produce a profit. COMMERCIAL USE examples:
 *
 * 1. OEMs (Original Equipment Manufacturers).
 * 2. ISVs (Independent Software Vendors).
 * 3. VARs (Value Added Resellers).
 * 4. Other distributors that combine and distribute commercially licensed
 *    software.
 *
 * IN CASE YOU WANT TO USE THE PRODUCT COMMERCIALLY, YOU MUST PURCHASE THE
 * APPROPRIATE LICENSE FROM "INFIMA" ONLINE STORE, STORE ADDRESS:
 *
 * 1. https://mathparser.org/order-commercial-license
 * 2. https://payhip.com/infima
 *
 * NON-COMMERCIAL LICENSE
 *
 * Redistribution and use of the PRODUCT in source and/or binary forms,
 * with or without modification, are permitted provided that the following
 * conditions are met:
 *
 * 1. Redistributions of source code must retain unmodified content of the
 *    entire MathParser.org-mXparser DUAL LICENSE AGREEMENT, including
 *    definition of NON-COMMERCIAL USE, definition of COMMERCIAL USE,
 *    NON-COMMERCIAL LICENSE conditions, COMMERCIAL LICENSE conditions, and
 *    the following DISCLAIMER.
 * 2. Redistributions in binary form must reproduce the entire content of
 *    MathParser.org-mXparser DUAL LICENSE AGREEMENT in the documentation
 *    and/or other materials provided with the distribution, including
 *    definition of NON-COMMERCIAL USE, definition of COMMERCIAL USE,
 *    NON-COMMERCIAL LICENSE conditions, COMMERCIAL LICENSE conditions, and
 *    the following DISCLAIMER.
 * 3. Any form of redistribution requires confirmation and signature of
 *    the NON-COMMERCIAL USE by successfully calling the method:
 *       License.iConfirmNonCommercialUse(...)
 *    The method call takes place only internally for logging purposes and
 *    there is no connection with other external services and no data is
 *    sent or collected. The lack of a method call (or its successful call)
 *    does not affect the operation of the PRODUCT in any way. Please see
 *    the API documentation.
 *
 * COMMERCIAL LICENSE
 *
 *  1. Before purchasing a commercial license, AUTHOR & PUBLISHER allow you
 *     to download, install and use up to three copies of the PRODUCT to
 *     perform integration tests, confirm the quality of the PRODUCT and
 *     its suitability. The testing period should be limited to fourteen
 *     days. Tests should be performed under the conditions of test
 *     environments. The purpose of the tests must not be to generate profit.
 *  2. Provided that you purchased a license from "INFIMA" online store
 *     (store address: https://mathparser.org/order-commercial-license or
 *     https://payhip.com/infima), and you comply with all below terms and
 *     conditions, and you have acknowledged and understood the following
 *     DISCLAIMER, AUTHOR & PUBLISHER grant you a nonexclusive license
 *     including the following rights:
 *  3. The license has been granted only to you, i.e., the person or entity
 *     that made the purchase, who is identified and confirmed by the data
 *     provided during the purchase.
 *  4. In case you purchased a license in the "ONE-TIME PURCHASE" model,
 *     the license has been granted only for the PRODUCT version specified
 *     in the purchase. The upgrade policy gives you additional rights and
 *     is described in the dedicated section below.
 *  5. In case you purchased a license in the "SUBSCRIPTION" model, you can
 *     install and use any version of the PRODUCT, but only during the
 *     subscription validity period.
 *  6. In case you purchased a "SINGLE LICENSE" you can install and use the
 *     PRODUCT from one workstation.
 *  7. Additional copies of the PRODUCT can be installed and used from more
 *     than one workstation; however, this number is limited to the number
 *     of workstations purchased as per order.
 *  8. In case you purchased a "SITE LICENSE ", the PRODUCT can be installed
 *     and used from all workstations located at your premises.
 *  9. You may incorporate the unmodified PRODUCT into your own products
 *     and software.
 * 10. If you purchased a license with the "SOURCE CODE" option, you may
 *     modify the PRODUCT's source code and incorporate the modified source
 *     code into your own products and/or software.
 * 11. Provided that the license validity period has not expired, you may
 *     distribute your product and/or software with the incorporated
 *     PRODUCT royalty-free.
 * 12. You may make copies of the PRODUCT for backup and archival purposes.
 * 13. Any form of redistribution requires confirmation and signature of
 *     the COMMERCIAL USE by successfully calling the method:
 *        License.iConfirmCommercialUse(...)
 *     The method call takes place only internally for logging purposes and
 *     there is no connection with other external services and no data is
 *     sent or collected. The lack of a method call (or its successful call)
 *     does not affect the operation of the PRODUCT in any way. Please see
 *     the API documentation.
 * 14. AUTHOR & PUBLISHER reserve all rights not expressly granted to you
 *     in this agreement.
 *
 * ADDITIONAL CLARIFICATION ON WORKSTATION
 *
 * The number of workstations does not relate to the final distribution of
 * your end-product to your end-users. In typical cases the number of
 * workstations is a way to measure the scale of the process of design
 * and/or development and/or creation and/or manufacturing of your product.
 *
 * A workstation is a device, a remote device, or a virtual device, used by
 * you, your employees, or other entities to whom you have commissioned the
 * tasks. For example, the number of workstations may refer to the number
 * of software developers, engineers, architects, scientists, and other
 * professionals who use the PRODUCT on your behalf. The number of
 * workstations is not the number of copies of your end-product that you
 * distribute to your end-users.
 *
 * By purchasing the COMMERCIAL LICENSE, you only pay for the number of
 * workstations, while the number of copies/users of your final product
 * (delivered to your end-users) is not limited.
 *
 * UPGRADE POLICY
 *
 * The PRODUCT is versioned according to the following convention:
 *
 *    [MAJOR].[MINOR].[PATCH]
 *
 * 1. COMMERCIAL LICENSE holders can install and use the updated version
 *    for bug fixes free of charge, i.e. if you have purchased a license
 *    for the [MAJOR].[MINOR] version (e.g.: 5.0), you can freely install
 *    all the various releases specified in the [PATCH] version (e.g.: 5.0.2).
 *    The license terms remain unchanged after the update.
 * 2. COMMERCIAL LICENSE holders for [MAJOR].[MINOR] version (e.g.: 5.0)
 *    can install and use the updated version [MAJOR].[MINOR + 1] free of
 *    charge, i.e., plus one release in the [MINOR] range (e.g.: 5.1). The
 *    license terms remain unchanged after the update.
 * 3. COMMERCIAL LICENSE holders who wish to upgrade their version, but are
 *    not eligible for the free upgrade, can claim a discount when
 *    purchasing the upgrade. For this purpose, please contact us via e-mail.
 *
 * DISCLAIMER
 *
 * THIS PRODUCT IS PROVIDED BY AUTHOR & PUBLISHER "AS IS" AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL AUTHOR OR PUBLISHER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS PRODUCT, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 *
 * THE VIEWS AND CONCLUSIONS CONTAINED IN THE PRODUCT AND DOCUMENTATION ARE
 * THOSE OF THE AUTHORS AND SHOULD NOT BE INTERPRETED AS REPRESENTING
 * OFFICIAL POLICIES, EITHER EXPRESSED OR IMPLIED, OF AUTHOR OR PUBLISHER.
 *
 * CONTACT
 *
 * - e-mail: info@mathparser.org
 * - website: https://mathparser.org
 * - source code: https://github.com/mariuszgromada/MathParser.org-mXparser
 * - online store: https://mathparser.org/order-commercial-license
 * - online store: https://payhip.com/infima
 */
package physics.org.mariuszgromada.math.mxparser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import physics.org.mariuszgromada.math.mxparser.parsertokens.ParserSymbol;
import physics.org.mariuszgromada.math.mxparser.parsertokens.Token;

/**
 * Function class provides possibility to define user functions.
 * Functions can be used in further processing by any expression,
 * dependent or recursive argument, function, etc... For example:
 *
 * <ul>
 * <li>'f(x) = sin(x)'
 * <li>'g(x,y) = sin(x)+cos(y)'
 * <li>'h(x,y = f(x)+g(y,x)'
 * <li>in general 'f(x1,x2,...,xn)' where x1,...,xn are arguments
 * </ul>
 *
 * <p>
 * When creating a function you should avoid names reserved as
 * parser keywords, in general words known in mathematical language
 * as function names, operators (for example:
 * sin, cos, +, -, pi, e, etc...). Please be informed that after associating
 * the constant with the expression, function or dependent/recursive argument
 * its name will be recognized by the parser as reserved key word.
 * It means that it could not be the same as any other key word known
 * by the parser for this particular expression.
 *
 * @author         <b>Mariusz Gromada</b><br>
 *                 <a href="https://mathparser.org" target="_blank">MathParser.org - mXparser project page</a><br>
 *                 <a href="https://github.com/mariuszgromada/MathParser.org-mXparser" target="_blank">mXparser on GitHub</a><br>
 *                 <a href="https://payhip.com/INFIMA" target="_blank">INFIMA place to purchase a commercial MathParser.org-mXparser software license</a><br>
 *                 <a href="mailto:info@mathparser.org">info@mathparser.org</a><br>
 *                 <a href="https://scalarmath.org/" target="_blank">ScalarMath.org - a powerful math engine and math scripting language</a><br>
 *                 <a href="https://play.google.com/store/apps/details?id=org.mathparser.scalar.lite" target="_blank">Scalar Lite</a><br>
 *                 <a href="https://play.google.com/store/apps/details?id=org.mathparser.scalar.pro" target="_blank">Scalar Pro</a><br>
 *                 <a href="https://mathspace.pl" target="_blank">MathSpace.pl</a><br>
 *
 * @version        5.2.0
 *
 * @see RecursiveArgument
 * @see Expression
 * @see Argument
 * @see Constant
 * @see FunctionExtension
 *
 */
public class Function extends PrimitiveElement implements Serializable {
	private static final int serialClassID = 3;
	private static final long serialVersionUID = SerializationUtils.getSerialVersionUID(serialClassID);
	/**
	 * Status of the syntax - no syntax error
	 */
	public static final boolean NO_SYNTAX_ERRORS = Expression.NO_SYNTAX_ERRORS;
	/**
	 * Status of the syntax - syntax error or syntax status unknown
	 */
	public static final boolean SYNTAX_ERROR = Expression.SYNTAX_ERROR;
	/**
	 * Status of the syntax - syntax error or syntax status unknown
	 *
	 * @deprecated Planned to be removed, use {@link #SYNTAX_ERROR} instead
	 */
	@Deprecated
	public static final boolean SYNTAX_ERROR_OR_STATUS_UNKNOWN = SYNTAX_ERROR;
	private static final boolean SYNTAX_STATUS_UNKNOWN = SYNTAX_ERROR;
	/**
	 * When function was not found
	 */
	public static final int NOT_FOUND = Expression.NOT_FOUND;
	/**
	 * Function type id identifier
	 */
	public static final int TYPE_ID = 103;
	public static String TYPE_DESC = ParserSymbol.NA;
	/**
	 * Function with body based on the expression string.
	 *
	 * @see Function#getFunctionBodyType()
	 */
	public static final int BODY_RUNTIME = 1;
	/**
	 * Function with body based on the extended code.
	 *
	 * @see FunctionExtension
	 * @see Function#getFunctionBodyType()
	 */
	public static final int BODY_EXTENDED = 2;
	/**
	 * Function body type.
	 *
	 * @see Function#BODY_RUNTIME
	 * @see Function#BODY_EXTENDED
	 * @see Function#getFunctionBodyType()
	 */
	private int functionBodyType = BODY_RUNTIME;
	/**
	 * function expression
	 */
	Expression functionExpression;
	/**
	 * function name
	 */
	private String functionName = StringInvariant.EMPTY;
	/**
	 * Syntax status registered on argument definition:
	 * - constructor, set name, ...
	 */
	private boolean syntaxStatusDefinition = SYNTAX_STATUS_UNKNOWN;
	/**
	 * Error Message registered on function definition:
	 * - constructor, set name, ...
	 */
	private String errorMessageDefinition = StringInvariant.EMPTY;
	/**
	 * function description
	 */
	private String description = StringInvariant.EMPTY;
	/**
	 * Indicates whether UDF is variadic
	 */
	boolean isVariadic = false;
	/**
	 * The number of function parameters
	 */
	private int parametersNumber = 0;
	/**
	 * Function extension (body based in code)
	 *
	 * @see FunctionExtension
	 * @see FunctionExtensionVariadic
	 * @see Function#Function(String, FunctionExtension)
	 */
	private FunctionExtension functionExtension;
	/**
	 * Function extension variadic (body based in code)
	 *
	 * @see FunctionExtension
	 * @see FunctionExtensionVariadic
	 * @see Function#Function(String, FunctionExtension)
	 */
	private FunctionExtensionVariadic functionExtensionVariadic;
	/**
	 * Keeps computing time
	 */
	double computingTime = 0;
	private boolean thisAlreadyAdded = false;
	/*=================================================
	 *
	 * Constructors
	 *
	 *=================================================
	 */
	private static String buildErrorMessageInvalidFunctionName(String functionName) {
		return StringModel.buildErrorMessagePatternDoesNotMatchWithExamples(functionName, StringModel.STRING_RESOURCES.INVALID_FUNCTION_NAME, StringInvariant.FUNCTION_NAME_EXAMPLES);
	}
	private static String buildErrorMessageInvalidFunctionDefinitionString(String functionDefinitionString) {
		return StringModel.buildErrorMessagePatternDoesNotMatchWithExamples(functionDefinitionString, StringModel.STRING_RESOURCES.INVALID_FUNCTION_DEFINITION, StringInvariant.FUNCTION_DEFINITION_EXAMPLES);
	}
	private static String buildErrorMessageIncorrectNumberOfFunctionParameters(String functionName, int expectedNumberOfParameters, int providedNumberOfParameters) {
		return StringModel.buildErrorMessageIncorrectNumberOfParameters(functionName, StringModel.STRING_RESOURCES.INCORRECT_NUMBER_OF_FUNCTION_PARAMETERS, expectedNumberOfParameters, providedNumberOfParameters);
	}
	private void registerNoSyntaxErrorInDefinition() {
		syntaxStatusDefinition = NO_SYNTAX_ERRORS;
		errorMessageDefinition = StringModel.STRING_RESOURCES.NO_ERRORS_DETECTED_IN_FUNCTION_DEFINITION;
		if (!thisAlreadyAdded) {
			addFunctions(this);
			thisAlreadyAdded = true;
		}
	}
	private void registerSyntaxErrorInDefinition(String errorMessage) {
		syntaxStatusDefinition = SYNTAX_ERROR;
		errorMessageDefinition = errorMessage;
		functionExpression = new Expression(StringInvariant.EMPTY);
	}
	/**
	 * Constructor - creates function from function name
	 * and function expression string.
	 *
	 * @param      functionName              the function name
	 * @param      functionExpressionString  the function expression string
	 * @param      elements                  Optional elements list (variadic - comma separated) of types: Argument, Constant, Function
	 *
	 * @see        PrimitiveElement
	 * @see        Expression
	 */
	public Function(String functionName, String functionExpressionString, PrimitiveElement... elements) {
		super(Function.TYPE_ID);
		if (functionName == null || functionExpressionString == null) {
			registerSyntaxErrorInDefinition(StringModel.STRING_RESOURCES.PROVIDED_STRING_IS_NULL);
			return;
		}
		if (elements == null) {
			registerSyntaxErrorInDefinition(StringModel.STRING_RESOURCES.PROVIDED_ELEMENTS_ARE_NULL);
			return;
		}
		String functionNameTrim = functionName.trim();
		if (!StringUtils.regexMatch(functionNameTrim, ParserSymbol.nameOnlyTokenRegExp)) {
			registerSyntaxErrorInDefinition(buildErrorMessageInvalidFunctionName(functionName));
			return;
		}
		String functionExpressionStringTrim = functionExpressionString.trim();
		this.functionName = functionNameTrim;
		functionExpression = new Expression(functionExpressionStringTrim, elements);
		functionExpression.setDescription(functionNameTrim);
		functionExpression.UDFExpression = true;
		registerNoSyntaxErrorInDefinition();
	}
	/**
	 * Constructor - creates function from function name,
	 * function expression string and argument names.
	 *
	 * @param      functionName              the function name
	 * @param      functionExpressionString  the function expression string
	 * @param      argumentsNames            the arguments names (variadic parameters)
	 *                                       comma separated list
	 *
	 * @see        Expression
	 */
	public Function(String functionName, String functionExpressionString, String... argumentsNames) {
		super(Function.TYPE_ID);
		if (functionName == null || functionExpressionString == null || argumentsNames == null) {
			registerSyntaxErrorInDefinition(StringModel.STRING_RESOURCES.PROVIDED_STRING_IS_NULL);
			return;
		}
		String functionNameTrim = functionName.trim();
		if (!StringUtils.regexMatch(functionNameTrim, ParserSymbol.nameOnlyTokenRegExp)) {
			registerSyntaxErrorInDefinition(buildErrorMessageInvalidFunctionName(functionName));
			return;
		}
		String functionExpressionStringTrim = functionExpressionString.trim();
		this.functionName = functionNameTrim;
		functionExpression = new Expression(functionExpressionStringTrim);
		functionExpression.setDescription(functionNameTrim);
		functionExpression.UDFExpression = true;
		for (String argName : argumentsNames)
			functionExpression.addArguments(new Argument(argName));
		parametersNumber = functionExpression.getArgumentsNumber() - countRecursiveArguments();
		registerNoSyntaxErrorInDefinition();
	}
	private void addHeadTokensArguments(List<Token> headTokens) {
		if (headTokens.size() <= 1)
			return;
		for (int i = 1; i < headTokens.size(); i++) {
			Token t = headTokens.get(i);
			if (t.tokenTypeId != ParserSymbol.TYPE_ID)
				functionExpression.addArguments(new Argument(t.tokenStr));
		}
	}
	/**
	 * Constructor for function definition in natural math language,
	 * for instance providing on string "f(x,y) = sin(x) + cos(x)"
	 * is enough to define function "f" with parameters "x and y"
	 * and function body "sin(x) + cos(x)".
	 *
	 * @param functionDefinitionString      Function definition in the form
	 *                                      of one String, ie "f(x,y) = sin(x) + cos(x)"
	 * @param elements                      Optional elements list (variadic - comma separated)
	 *                                      of types: Argument, Constant, Function
	 *
	 * @see    PrimitiveElement
	 *
	 */
	public Function(String functionDefinitionString, PrimitiveElement... elements) {
		super(Function.TYPE_ID);
		if (functionDefinitionString == null) {
			registerSyntaxErrorInDefinition(StringModel.STRING_RESOURCES.PROVIDED_STRING_IS_NULL);
			return;
		}
		if (elements == null) {
			registerSyntaxErrorInDefinition(StringModel.STRING_RESOURCES.PROVIDED_ELEMENTS_ARE_NULL);
			return;
		}
		String functionDefinitionStringTrim = functionDefinitionString.trim();
		if (StringUtils.regexMatch(functionDefinitionStringTrim, ParserSymbol.functionDefStrRegExp)) {
			HeadEqBody headEqBody = new HeadEqBody(functionDefinitionStringTrim);
			functionName = headEqBody.headTokens.get(0).tokenStr;
			functionExpression = new Expression(headEqBody.bodyStr, elements);
			functionExpression.setDescription(headEqBody.headStr);
			functionExpression.UDFExpression = true;
			addHeadTokensArguments(headEqBody.headTokens);
			parametersNumber = functionExpression.getArgumentsNumber() - countRecursiveArguments();
			registerNoSyntaxErrorInDefinition();
			return;
		}
		if (StringUtils.regexMatch(functionDefinitionStringTrim, ParserSymbol.functionVariadicDefStrRegExp)) {
			HeadEqBody headEqBody = new HeadEqBody(functionDefinitionStringTrim);
			functionName = headEqBody.headTokens.get(0).tokenStr;
			functionExpression = new Expression(headEqBody.bodyStr, elements);
			functionExpression.setDescription(headEqBody.headStr);
			functionExpression.UDFExpression = true;
			isVariadic = true;
			parametersNumber = -1;
			registerNoSyntaxErrorInDefinition();
			return;
		}
		registerSyntaxErrorInDefinition(buildErrorMessageInvalidFunctionDefinitionString(functionDefinitionStringTrim));
	}
	/**
	 * Constructor for function definition based on
	 * your own source code - this is via implementation
	 * of FunctionExtension interface.
	 *
	 * @param functionName       Function name
	 * @param functionExtension  Your own source code
	 */
	public Function(String functionName, FunctionExtension functionExtension) {
		super(Function.TYPE_ID);
		if (functionName == null) {
			registerSyntaxErrorInDefinition(StringModel.STRING_RESOURCES.PROVIDED_STRING_IS_NULL);
			return;
		}
		if (functionExtension == null) {
			registerSyntaxErrorInDefinition(StringModel.STRING_RESOURCES.PROVIDED_EXTENSION_IS_NULL);
			return;
		}
		String functionNameTrim = functionName.trim();
		if (!StringUtils.regexMatch(functionNameTrim, ParserSymbol.nameOnlyTokenRegExp)) {
			registerSyntaxErrorInDefinition(buildErrorMessageInvalidFunctionName(functionName));
			return;
		}
		this.functionName = functionNameTrim;
		functionExpression = new Expression(StringInvariant.BODY_EXTENDED);
		functionExpression.setDescription(functionNameTrim);
		parametersNumber = functionExtension.getParametersNumber();
		this.functionExtension = functionExtension;
		functionBodyType = BODY_EXTENDED;
		registerNoSyntaxErrorInDefinition();
	}
	/**
	 * Constructor for function definition based on
	 * your own source code - this is via implementation
	 * of FunctionExtensionVariadic interface.
	 *
	 * @param functionName       Function name
	 * @param functionExtensionVariadic  Your own source code
	 */
	public Function(String functionName, FunctionExtensionVariadic functionExtensionVariadic) {
		super(Function.TYPE_ID);
		if (functionName == null) {
			registerSyntaxErrorInDefinition(StringModel.STRING_RESOURCES.PROVIDED_STRING_IS_NULL);
			return;
		}
		if (functionExtensionVariadic == null) {
			registerSyntaxErrorInDefinition(StringModel.STRING_RESOURCES.PROVIDED_EXTENSION_IS_NULL);
			return;
		}
		String functionNameTrim = functionName.trim();
		if (!StringUtils.regexMatch(functionNameTrim, ParserSymbol.nameOnlyTokenRegExp)) {
			registerSyntaxErrorInDefinition(buildErrorMessageInvalidFunctionName(functionName));
			return;
		}
		this.functionName = functionNameTrim;
		functionExpression = new Expression(StringInvariant.BODY_EXTENDED_VARIADIC);
		functionExpression.setDescription(functionNameTrim);
		isVariadic = true;
		parametersNumber = -1;
		this.functionExtensionVariadic = functionExtensionVariadic;
		functionBodyType = BODY_EXTENDED;
		registerNoSyntaxErrorInDefinition();
	}
	/**
	 * Private constructor used for function cloning.
	 *
	 * @param      functionToClone     the function, which is going
	 *                                 to be cloned.
	 */
	private Function(Function functionToClone, boolean isThreadSafeClone, CloneCache cloneCache) {
		super(Function.TYPE_ID);

		functionBodyType = functionToClone.functionBodyType;
		functionName = functionToClone.functionName;
		syntaxStatusDefinition = functionToClone.syntaxStatusDefinition;
		errorMessageDefinition = functionToClone.errorMessageDefinition;
		description = functionToClone.description;
		isVariadic = functionToClone.isVariadic;
		parametersNumber = functionToClone.parametersNumber;
		computingTime = functionToClone.computingTime;
		thisAlreadyAdded = functionToClone.thisAlreadyAdded;

		if (functionToClone.functionExtension != null)
			functionExtension = functionToClone.functionExtension.clone();
		if (functionToClone.functionExtensionVariadic != null)
			functionExtensionVariadic = functionToClone.functionExtensionVariadic.clone();

		if (isThreadSafeClone) {
			functionExpression = functionToClone.functionExpression.cloneForThreadSafeInternal(cloneCache);
			return;
		}

		functionExpression = functionToClone.functionExpression.clone();
	}
	/**
	 * Constructor for function definition in natural math language,
	 * for instance providing on string "f(x,y) = sin(x) + cos(x)"
	 * is enough to define function "f" with parameters "x and y"
	 * and function body "sin(x) + cos(x)".
	 *
	 * @param functionDefinitionString      Function definition in the form
	 *                                      of one String, ie "f(x,y) = sin(x) + cos(x)"
	 * @param elements                      Optional elements list (variadic - comma separated)
	 *                                      of types: Argument, Constant, Function
	 *
	 * @see    PrimitiveElement
	 *
	 * @deprecated Planned to be removed, use {@link #addFunctions(Function...)}, {@link #addDefinitions(PrimitiveElement...)} instead
	 *
	 */
	@Deprecated
	public void setFunction(String functionDefinitionString, PrimitiveElement... elements) {
		if (StringUtils.regexMatch(functionDefinitionString, ParserSymbol.functionDefStrRegExp)) {
			HeadEqBody headEqBody = new HeadEqBody(functionDefinitionString);
			this.functionName = headEqBody.headTokens.get(0).tokenStr;
			functionExpression = new Expression(headEqBody.bodyStr, elements);
			functionExpression.setDescription(headEqBody.headStr);
			functionExpression.UDFExpression = true;
			isVariadic = false;
			addHeadTokensArguments(headEqBody.headTokens);
			parametersNumber = functionExpression.getArgumentsNumber() - countRecursiveArguments();
			description = StringInvariant.EMPTY;
			functionBodyType = BODY_RUNTIME;
			registerNoSyntaxErrorInDefinition();
			return;
		}
		if (StringUtils.regexMatch(functionDefinitionString, ParserSymbol.functionVariadicDefStrRegExp)) {
			HeadEqBody headEqBody = new HeadEqBody(functionDefinitionString);
			this.functionName = headEqBody.headTokens.get(0).tokenStr;
			functionExpression = new Expression(headEqBody.bodyStr, elements);
			functionExpression.setDescription(headEqBody.headStr);
			functionExpression.UDFExpression = true;
			isVariadic = true;
			parametersNumber = -1;
			description = StringInvariant.EMPTY;
			functionBodyType = BODY_RUNTIME;
			registerNoSyntaxErrorInDefinition();
			return;
		}
		registerSyntaxErrorInDefinition(buildErrorMessageInvalidFunctionDefinitionString(functionDefinitionString));
	}
	/**
	 * Sets function description.
	 *
	 * @param      description         the function description
	 */
	public void setDescription(String description) {
		if (description == null)
			return;
		this.description = description;
	}
	/**
	 * Gets function description
	 *
	 * @return     Function description as string
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Gets function name.
	 *
	 * @return     Function name as string.
	 */
	public String getFunctionName() {
		return functionName;
	}
	/**
	 * Gets function expression string
	 *
	 * @return     Function expression as string.
	 */
	public String getFunctionExpressionString() {
		return functionExpression.getExpressionString();
	}
	/**
	 * Sets function name.
	 *
	 * @param      functionName        the function name
	 */
	public void setFunctionName(String functionName) {
		if (functionName == null) {
			if (!syntaxStatusDefinition)
				registerSyntaxErrorInDefinition(StringModel.STRING_RESOURCES.PROVIDED_STRING_IS_NULL);
			return;
		}
		String functionNameTrim = functionName.trim();
		if (this.functionName.equals(functionNameTrim))
			return;
		if (!StringUtils.regexMatch(functionNameTrim, ParserSymbol.nameOnlyTokenRegExp)) {
			if (!syntaxStatusDefinition)
				registerSyntaxErrorInDefinition(buildErrorMessageInvalidFunctionName(functionName));
			return;
		}
		this.functionName = functionNameTrim;
		registerNoSyntaxErrorInDefinition();
		setExpressionModifiedFlags();
	}
	/**
	 * Sets value of function argument (function parameter).
	 *
	 * @param      argumentIndex   the argument index (in accordance to
	 *                             arguments declaration sequence)
	 * @param      argumentValue   the argument value
	 */
	public void setArgumentValue(int argumentIndex, double argumentValue) {
		if (argumentIndex < 0 || argumentIndex >= parametersNumber)
			return;
		if (isVariadic)
			return;
		if (functionBodyType == BODY_RUNTIME)
			functionExpression.argumentsList.get(argumentIndex).argumentValue = argumentValue;
		else
			functionExtension.setParameterValue(argumentIndex, argumentValue);
	}
	/**
	 * Returns function body type: {@link Function#BODY_RUNTIME} {@link Function#BODY_EXTENDED}
	 * @return Returns function body type: {@link Function#BODY_RUNTIME} {@link Function#BODY_EXTENDED}
	 */
	public int getFunctionBodyType() {
		return functionBodyType;
	}
	/**
	 * Checks function syntax
	 *
	 * @return     syntax status: Function.NO_SYNTAX_ERRORS,
	 *                            Function.SYNTAX_ERROR
	 *
	 */
	public boolean checkSyntax() {
		if (!syntaxStatusDefinition)
			return SYNTAX_ERROR;
		if (functionName.length() == 0)
			return SYNTAX_ERROR;
		if (functionBodyType == BODY_EXTENDED) {
			if (!isVariadic && functionExtension == null)
				return SYNTAX_ERROR;
			if (isVariadic && functionExtensionVariadic == null)
				return SYNTAX_ERROR;
			return NO_SYNTAX_ERRORS;
		}
		boolean syntaxStatus = functionExpression.checkSyntax();
		checkRecursiveMode();
		return syntaxStatus;
	}
	/**
	 * Returns error message after checking the syntax.
	 *
	 * @return     Error message as string.
	 */
	public String getErrorMessage() {
		if (!syntaxStatusDefinition)
			return errorMessageDefinition;
		if (functionName.length() == 0)
			return errorMessageDefinition;
		if (functionBodyType == BODY_EXTENDED) {
			if (!isVariadic && functionExtension == null)
				return errorMessageDefinition;
			if (isVariadic && functionExtensionVariadic == null)
				return errorMessageDefinition;
		}
		String functionExpressionErrorMessage = functionExpression.getErrorMessage();
		if (errorMessageDefinition.length() > 0 && functionExpressionErrorMessage.length() == 0)
			return errorMessageDefinition;
		return functionExpressionErrorMessage;
	}
	/**
	 * clone method
	 */
	protected Function clone() {
		return new Function(this, false, null);
	}
	/**
	 * Calculates function value
	 *
	 * @return     Function value as double.
	 */
	public double calculate() {
		return calculate((CalcStepsRegister) null);
	}
	/**
	 * Calculates function value and registers all the calculation steps
	 *
	 * @param calcStepsRegister A collection to store list of calculation steps,
	 *                          steps registered as strings.
	 *
	 * @return     Function value as double.
	 */
	public double calculate(CalcStepsRegister calcStepsRegister) {
		CalcStepsRegister.setUserFunction(calcStepsRegister, this);
		computingTime = 0;
		if (!syntaxStatusDefinition)
			return Double.NaN;
		double value = Double.NaN;
		if (functionBodyType == BODY_RUNTIME) {
			value = functionExpression.calculate(calcStepsRegister);
			computingTime = functionExpression.computingTime;
			return value;
		}
		License.checkLicense();
		long startTime = System.currentTimeMillis();
		if (!isVariadic) {
			value = functionExtension.calculate();
			computingTime = (System.currentTimeMillis() - startTime)/1000.0;
			return value;
		}
		List<Double> paramsList = functionExpression.UDFVariadicParamsAtRunTime;
		if (paramsList != null) {
			int n = paramsList.size();
			double[] parameters = new double[n];
			for (int i = 0; i < n; i++)
				parameters[i] = paramsList.get(i);
			value = functionExtensionVariadic.calculate(parameters);
		}
		computingTime = (System.currentTimeMillis() - startTime)/1000.0;
		return value;
	}
	/**
	 * Calculates function value
	 *
	 * @param      parameters              the function parameters values (as doubles)
	 *
	 * @return     function value as double.
	 */
	public double calculate(double... parameters) {
		computingTime = 0;
		if (!syntaxStatusDefinition)
			return Double.NaN;
		if (parameters == null)
			return Double.NaN;
		if (parameters.length == 0)
			return Double.NaN;

		long startTime = System.currentTimeMillis();
		functionExpression.UDFVariadicParamsAtRunTime = new ArrayList<Double>();
		for (double x : parameters)
			functionExpression.UDFVariadicParamsAtRunTime.add(x);

		double value = Double.NaN;
		if (isVariadic) {
			if (functionBodyType == BODY_RUNTIME) {
				value =  functionExpression.calculate();
				computingTime = functionExpression.computingTime;
				return value;
			}
			value = functionExtensionVariadic.calculate(parameters);
			computingTime = (System.currentTimeMillis() - startTime)/1000.0;
			return value;
		}

		if (parameters.length != this.getParametersNumber()) {
			functionExpression.setSyntaxStatus(SYNTAX_ERROR, buildErrorMessageIncorrectNumberOfFunctionParameters(functionName, getParametersNumber(), parameters.length));
			return Double.NaN;
		}

		if (functionBodyType == BODY_RUNTIME) {
			for (int p = 0; p < parameters.length; p++)
				setArgumentValue(p, parameters[p]);
			value = functionExpression.calculate();
			computingTime = functionExpression.computingTime;
			return value;
		}

		for (int p = 0; p < parameters.length; p++)
			functionExtension.setParameterValue(p, parameters[p]);

		value = functionExtension.calculate();
		computingTime = (System.currentTimeMillis() - startTime)/1000.0;
		return value;
	}
	/**
	 * Calculates function value
	 *
	 * @param      arguments   function parameters (as Arguments)
	 *
	 * @return     function value as double
	 */
	public double calculate(Argument... arguments) {
		computingTime = 0;
		if (!syntaxStatusDefinition)
			return Double.NaN;
		if (arguments == null)
			return Double.NaN;
		if (arguments.length == 0)
			return Double.NaN;

		long startTime = System.currentTimeMillis();
		double[] parameters;
		functionExpression.UDFVariadicParamsAtRunTime = new ArrayList<Double>();
		parameters = new double[arguments.length];
		double x;
		for (int i = 0; i < arguments.length; i++) {
			x = arguments[i].getArgumentValue();
			functionExpression.UDFVariadicParamsAtRunTime.add(x);
			parameters[i] = x;
		}

		double value = Double.NaN;
		if (isVariadic) {
			if (functionBodyType == BODY_RUNTIME) {
				value = functionExpression.calculate();
				computingTime = functionExpression.computingTime;
				return value;
			}
			value = functionExtensionVariadic.calculate(parameters);
			computingTime = (System.currentTimeMillis() - startTime)/1000.0;
			return value;
		}

		if (arguments.length != this.getParametersNumber()) {
			functionExpression.setSyntaxStatus(SYNTAX_ERROR, buildErrorMessageIncorrectNumberOfFunctionParameters(functionName, getParametersNumber(), arguments.length));
			return Double.NaN;
		}

		if (functionBodyType == BODY_RUNTIME) {
			for (int p = 0; p < arguments.length; p++)
				setArgumentValue(p, arguments[p].getArgumentValue());
			value = functionExpression.calculate();
			computingTime = functionExpression.computingTime;
			return value;
		}
		for (int p = 0; p < arguments.length; p++)
			functionExtension.setParameterValue(p, arguments[p].getArgumentValue());

		value = functionExtension.calculate();
		computingTime = (System.currentTimeMillis() - startTime)/1000.0;
		return value;
	}
	/**
	 * Adds user defined elements (such as: Arguments, Constants, Functions)
	 * to the function expressions.
	 *
	 * @param elements Elements list (variadic), where Argument, Constant, Function
	 *                 extend the same class PrimitiveElement
	 *
	 * @see PrimitiveElement
	 */
	public void addDefinitions(PrimitiveElement... elements) {
		if (elements == null)
			return;
		if (functionBodyType == Function.BODY_RUNTIME)
			functionExpression.addDefinitions(elements);
	}
	/**
	 * Removes user defined elements (such as: Arguments, Constants, Functions)
	 * from the function expressions.
	 *
	 * @param elements Elements list (variadic), where Argument, Constant, Function
	 *                 extend the same class PrimitiveElement
	 *
	 * @see PrimitiveElement
	 */
	public void removeDefinitions(PrimitiveElement... elements) {
		if (elements == null)
			return;
		if (functionBodyType == Function.BODY_RUNTIME)
			functionExpression.removeDefinitions(elements);
	}
	/*=================================================
	 *
	 * Arguments handling API (the same as in Expression)
	 * (protected argument expression)
	 *
	 *=================================================
	 */
	private int countRecursiveArguments() {
		if (functionBodyType != Function.BODY_RUNTIME)
			return 0;
		int numOfRecursiveArguments = 0;
		for (Argument argument : functionExpression.argumentsList)
			if (argument.getArgumentType() == Argument.RECURSIVE_ARGUMENT) numOfRecursiveArguments++;
		return numOfRecursiveArguments;
	}
	/**
	 * Adds arguments (variadic) to the function expression definition.
	 *
	 * @param      arguments           the arguments list
	 *                                 (comma separated list)
	 * @see        Argument
	 * @see        RecursiveArgument
	 */
	public void addArguments(Argument... arguments) {
		if (arguments == null)
			return;
		if (functionBodyType != Function.BODY_RUNTIME)
			return;
		functionExpression.addArguments(arguments);
		//parametersNumber = functionExpression.getArgumentsNumber() - countRecursiveArguments();
	}
	/**
	 * Enables to define the arguments (associated with
	 * the function expression) based on the given arguments names.
	 *
	 * @param      argumentsNames      the arguments names (variadic)
	 *                                 comma separated list
	 *
	 * @see        Argument
	 * @see        RecursiveArgument
	 */
	public void defineArguments(String... argumentsNames) {
		if (argumentsNames == null)
			return;
		if (functionBodyType != Function.BODY_RUNTIME)
			return;
		functionExpression.defineArguments(argumentsNames);
		parametersNumber = functionExpression.getArgumentsNumber() - countRecursiveArguments();
	}
	/**
	 * Enables to define the argument (associated with the function expression)
	 * based on the argument name and the argument value.
	 *
	 * @param      argumentName        the argument name
	 * @param      argumentValue       the argument value
	 *
	 * @see        Argument
	 * @see        RecursiveArgument
	 */
	public void defineArgument(String argumentName, double argumentValue) {
		if (argumentName == null)
			return;
		if (functionBodyType != Function.BODY_RUNTIME)
			return;
		functionExpression.defineArgument(argumentName, argumentValue);
		parametersNumber = functionExpression.getArgumentsNumber() - countRecursiveArguments();
	}
	/**
	 * Gets argument index from the function expression.
	 *
	 * @param      argumentName        the argument name
	 *
	 * @return     The argument index if the argument name was found,
	 *             otherwise returns Argument.NOT_FOUND
	 *
	 * @see        Argument
	 * @see        RecursiveArgument
	 */
	public int getArgumentIndex(String argumentName) {
		if (argumentName == null)
			return -1;
		if (functionBodyType != Function.BODY_RUNTIME)
			return -1;
		return functionExpression.getArgumentIndex(argumentName);
	}
	/**
	 * Gets argument from the function expression.
	 *
	 *
	 * @param      argumentName        the argument name
	 *
	 * @return     The argument if the argument name was found,
	 *             otherwise returns null.
	 *
	 * @see        Argument
	 * @see        RecursiveArgument
	 */
	public Argument getArgument(String argumentName) {
		if (argumentName == null)
			return null;
		if (functionBodyType != Function.BODY_RUNTIME)
			return null;
		return functionExpression.getArgument(argumentName);
	}
	/**
	 * Gets argument from the function expression.
	 *
	 * @param      argumentIndex       the argument index
	 *
	 * @return     Argument if the argument index is between 0 and
	 *             the last available argument index (getArgumentsNumber()-1),
	 *             otherwise returns null.
	 *
	 * @see        Argument
	 * @see        RecursiveArgument
	 */
	public Argument getArgument(int argumentIndex) {
		if (argumentIndex < 0)
			return null;
		if (functionBodyType != Function.BODY_RUNTIME)
			return null;
		return functionExpression.getArgument(argumentIndex);
	}
	/**
	 * Gets number of parameters associated with the function expression.
	 *
	 * @return     The number of function parameters (int &gt;= 0)
	 *
	 * @see        Argument
	 * @see        RecursiveArgument
	 */
	public int getParametersNumber() {
		if (!isVariadic)
			return parametersNumber;
		if (functionExpression.UDFVariadicParamsAtRunTime == null)
			return -1;
		return functionExpression.UDFVariadicParamsAtRunTime.size();
	}
	/**
	 * Set parameters number.
	 *
	 * @param      parametersNumber    the number of function parameters (default = number of arguments
	 *                                 (lower number might be specified).
	 */
	public void setParametersNumber(int parametersNumber) {
		if (parametersNumber < 0)
			return;
		if (functionBodyType != Function.BODY_RUNTIME)
			return;
		this.parametersNumber = parametersNumber;
		functionExpression.setExpressionModifiedFlag();
	}
	/**
	 * Gets user defined function parameter name
	 *
	 * @param parameterIndex  Parameter index between 0 and n-1
	 * @return If parameter exists returns parameters name, otherwise empty string is returned.
	 */
	public String getParameterName(int parameterIndex) {
		if (parameterIndex < 0) return StringInvariant.EMPTY;
		if (parameterIndex >= parametersNumber) return StringInvariant.EMPTY;
		if (functionBodyType == BODY_RUNTIME) {
			Argument x = getArgument(parameterIndex);
			if (x == null)
				return StringInvariant.EMPTY;
			return x.getArgumentName();
		}
		if (functionBodyType == BODY_EXTENDED) return this.functionExtension.getParameterName(parameterIndex);
		return StringInvariant.EMPTY;
	}
	/**
	 * Gets number of arguments associated with the function expression.
	 *
	 * @return     The number of arguments (int &gt;= 0)
	 *
	 * @see        Argument
	 * @see        RecursiveArgument
	 */
	public int getArgumentsNumber() {
		if (functionBodyType != Function.BODY_RUNTIME)
			return 0;
		return functionExpression.getArgumentsNumber();
	}
	/**
	 * Removes first occurrences of the arguments
	 * associated with the function expression.
	 *
	 * @param      argumentsNames      the arguments names
	 *                                 (variadic parameters) comma separated
	 *                                 list
	 *
	 * @see        Argument
	 * @see        RecursiveArgument
	 */
	public void removeArguments(String... argumentsNames) {
		if (argumentsNames == null)
			return;
		if (functionBodyType != Function.BODY_RUNTIME)
			return;
		functionExpression.removeArguments(argumentsNames);
		parametersNumber = functionExpression.getArgumentsNumber() - countRecursiveArguments();
	}
	/**
	 * Removes first occurrences of the arguments
	 * associated with the function expression.
	 *
	 * @param      arguments           the arguments (variadic parameters)
	 *                                 comma separated list
	 *
	 * @see        Argument
	 * @see        RecursiveArgument
	 */
	public void removeArguments(Argument... arguments) {
		if (arguments == null)
			return;
		if (functionBodyType != Function.BODY_RUNTIME)
			return;
		functionExpression.removeArguments(arguments);
		parametersNumber = functionExpression.getArgumentsNumber() - countRecursiveArguments();
	}
	/**
	 * Removes all arguments associated with the function expression.
	 *
	 * @see        Argument
	 * @see        RecursiveArgument
	 */
	public void removeAllArguments() {
		if (functionBodyType != Function.BODY_RUNTIME)
			return;
		functionExpression.removeAllArguments();
		parametersNumber = 0;
	}
	/*=================================================
	 *
	 * Constants handling API (the same as in Expression)
	 * (protected argument expression)
	 *
	 *=================================================
	 */
	/**
	 * Adds constants (variadic parameters) to the function expression definition.
	 *
	 * @param      constants           the constants
	 *                                 (comma separated list)
	 *
	 * @see        Constant
	 */
	public void addConstants(Constant... constants) {
		if (constants == null)
			return;
		if (functionBodyType == Function.BODY_RUNTIME)
			functionExpression.addConstants(constants);
	}
	/**
	 * Adds constants to the function expression definition.
	 *
	 * @param      constantsList       the list of constants
	 *
	 * @see        Constant
	 */
	public void addConstants(List<Constant> constantsList) {
		if (constantsList == null)
			return;
		if (functionBodyType == Function.BODY_RUNTIME)
			functionExpression.addConstants(constantsList);
	}
	/**
	 * Enables to define the constant (associated with
	 * the function expression) based on the constant name and
	 * constant value.
	 *
	 * @param      constantName        the constant name
	 * @param      constantValue       the constant value
	 *
	 * @see        Constant
	 */
	public void defineConstant(String constantName, double constantValue) {
		if (constantName == null)
			return;
		if (functionBodyType == Function.BODY_RUNTIME)
			functionExpression.defineConstant(constantName, constantValue);
	}
	/**
	 * Gets constant index associated with the function expression.
	 *
	 * @param      constantName        the constant name
	 *
	 * @return     Constant index if constant name was found,
	 *             otherwise return Constant.NOT_FOUND.
	 *
	 * @see        Constant
	 */
	public int getConstantIndex(String constantName) {
		if (constantName == null)
			return -1;
		if (functionBodyType != Function.BODY_RUNTIME)
			return -1;
		return functionExpression.getConstantIndex(constantName);
	}
	/**
	 * Gets constant associated with the function expression.
	 *
	 * @param      constantName        the constant name
	 *
	 * @return     Constant if constant name was found,
	 *             otherwise return null.
	 *
	 * @see        Constant
	 */
	public Constant getConstant(String constantName) {
		if (constantName == null)
			return null;
		if (functionBodyType != Function.BODY_RUNTIME)
			return null;
		return functionExpression.getConstant(constantName);
	}
	/**
	 * Gets constant associated with the function expression.
	 *
	 * @param      constantIndex       the constant index
	 *
	 * @return     Constant if the constantIndex is between
	 *             0 and the last available constant index
	 *             (getConstantsNumber() - 1),
	 *             otherwise it returns null.
	 *
	 * @see        Constant
	 */
	public Constant getConstant(int constantIndex) {
		if (constantIndex < 0)
			return null;
		if (functionBodyType != Function.BODY_RUNTIME)
			return null;
		return functionExpression.getConstant(constantIndex);
	}
	/**
	 * Gets number of constants associated with the function expression.
	 *
	 * @return     number of constants (int &gt;= 0)
	 *
	 * @see        Constant
	 */
	public int getConstantsNumber() {
		if (functionBodyType != Function.BODY_RUNTIME)
			return 0;
		return functionExpression.getConstantsNumber();
	}
	/**
	 * Removes first occurrences of the constants
	 * associated with the function expression.
	 *
	 * @param      constantsNames      the constants names (variadic parameters)
	 *                                 comma separated list
	 *
	 * @see        Constant
	 */
	public void removeConstants(String... constantsNames) {
		if (constantsNames == null)
			return;
		if (functionBodyType == Function.BODY_RUNTIME)
			functionExpression.removeConstants(constantsNames);
	}
	/**
	 * Removes first occurrences of the constants
	 * associated with the function expression
	 *
	 * @param      constants           the constants (variadic parameters)
	 *                                 comma separated list
	 *
	 * @see        Constant
	 */
	public void removeConstants(Constant... constants) {
		if (constants == null)
			return;
		if (functionBodyType == Function.BODY_RUNTIME)
			functionExpression.removeConstants(constants);
	}
	/**
	 * Removes all constants
	 * associated with the function expression
	 *
	 * @see        Constant
	 */
	public void removeAllConstants() {
		if (functionBodyType == Function.BODY_RUNTIME)
			functionExpression.removeAllConstants();
	}
	/*=================================================
	 *
	 * Functions handling API (the same as in Expression)
	 * (protected argument expression)
	 *
	 *=================================================
	 */
	/**
	 * Adds functions (variadic parameters) to the function expression definition.
	 *
	 * @param      functions           the functions
	 *                                 (variadic parameters) comma separated list
	 *
	 * @see        Function
	 */
	public void addFunctions(Function... functions) {
		if (functions == null)
			return;
		if (functionBodyType == Function.BODY_RUNTIME)
			functionExpression.addFunctions(functions);
	}
	/**
	 * Enables to define the function (associated with
	 * the function expression) based on the function name,
	 * function expression string and arguments names (variadic parameters).
	 *
	 * @param      functionName                  the function name
	 * @param      functionExpressionString      the expression string
	 * @param      argumentsNames                the function arguments names
	 *                                           (variadic parameters)
	 *                                           comma separated list
	 *
	 * @see        Function
	 */
	public void defineFunction(String functionName, String functionExpressionString, String... argumentsNames) {
		if (functionName == null || functionExpressionString == null || argumentsNames == null)
			return;
		if (functionBodyType == Function.BODY_RUNTIME)
			functionExpression.defineFunction(functionName, functionExpressionString, argumentsNames);
	}
	/**
	 * Gets index of function associated with the function expression.
	 *
	 * @param      functionName        the function name
	 *
	 * @return     Function index if function name was found,
	 *             otherwise returns Function.NOT_FOUND
	 *
	 * @see        Function
	 */
	public int getFunctionIndex(String functionName) {
		if (functionName == null)
			return -1;
		if (functionBodyType != Function.BODY_RUNTIME)
			return -1;
		return functionExpression.getFunctionIndex(functionName);
	}
	/**
	 * Gets function associated with the function expression.
	 *
	 * @param      functionName        the function name
	 *
	 * @return     Function if function name was found,
	 *             otherwise returns null.
	 *
	 * @see        Function
	 */
	public Function getFunction(String functionName) {
		if (functionName == null)
			return null;
		if (functionBodyType != Function.BODY_RUNTIME)
			return null;
		return functionExpression.getFunction(functionName);
	}
	/**
	 * Gets function associated with the function expression.
	 *
	 * @param      functionIndex the function index
	 *
	 * @return     Function if function index is between 0 and
	 *             the last available function index (getFunctionsNumber()-1),
	 *             otherwise returns null.
	 *
	 * @see        Function
	 */
	public Function getFunction(int functionIndex) {
		if (functionIndex < 0)
			return null;
		if (functionBodyType != Function.BODY_RUNTIME)
			return null;
		return functionExpression.getFunction(functionIndex);
	}
	/**
	 * Gets number of functions associated with the function expression.
	 *
	 * @return     number of functions (int &gt;= 0)
	 *
	 * @see        Function
	 */
	public int getFunctionsNumber() {
		if (functionBodyType != Function.BODY_RUNTIME)
			return 0;
		int recursiveThisCorrection = 0;
		for (Function f : functionExpression.functionsList)
			if (this == f)
				recursiveThisCorrection++;
		return functionExpression.getFunctionsNumber() - recursiveThisCorrection;
	}
	/**
	 * Removes first occurrences of the functions
	 * associated with the function expression.
	 *
	 * @param      functionsNames      the functions names (variadic parameters)
	 *                                 comma separated list
	 *
	 * @see        Function
	 */
	public void removeFunctions(String... functionsNames) {
		if (functionsNames == null)
			return;
		if (functionBodyType == Function.BODY_RUNTIME)
			functionExpression.removeFunctions(functionsNames);
	}
	/**
	 * Removes first occurrences of the functions
	 * associated with the function expression.
	 *
	 * @param      functions           the functions (variadic parameters)
	 *                                 comma separated list.
	 *
	 * @see        Function
	 */
	public void removeFunctions(Function... functions) {
		if (functions == null)
			return;
		if (functionBodyType == Function.BODY_RUNTIME)
			functionExpression.removeFunctions(functions);
	}
	/**
	 * Removes all functions
	 * associated with the function expression.
	 *
	 * @see        Function
	 */
	public void removeAllFunctions() {
		if (functionBodyType == Function.BODY_RUNTIME)
			functionExpression.removeAllFunctions();
	}
	/**
	 * Enables verbose function mode
	 */
	public void setVerboseMode() {
		functionExpression.setVerboseMode();
	}
	/**
	 * Disables function verbose mode (sets default silent mode)
	 */
	public void setSilentMode() {
		functionExpression.setSilentMode();
	}
	/**
	 * Returns verbose mode status
	 *
	 * @return     true if verbose mode is on,
	 *             otherwise returns false
	 */
	public boolean getVerboseMode() {
		return functionExpression.getVerboseMode();
	}
	/**
	 * Checks whether function name appears in function body
	 * if yes the recursive mode is being set
	 */
	void checkRecursiveMode() {
		if (functionBodyType != Function.BODY_RUNTIME)
			return;
		if (functionExpression.initialTokens == null)
			return;
		functionExpression.disableRecursiveMode();
		for (Token t : functionExpression.initialTokens)
			if (t.tokenStr.equals(functionName)) {
				functionExpression.setRecursiveMode();
				break;
			}
	}
	/**
	 * Gets recursive mode status
	 *
	 * @return     true if recursive mode is enabled,
	 *             otherwise returns false
	 *
	 */
	public boolean getRecursiveMode() {
		return functionExpression.getRecursiveMode();
	}
	/**
	 * Gets computing time
	 *
	 * @return     computing time in seconds.
	 */
	public double getComputingTime() {
		return computingTime;
	}
	/**
	 * Adds related expression.
	 *
	 * @param      expression          the related expression
	 */
	void addRelatedExpression(Expression expression) {
		if (functionBodyType == Function.BODY_RUNTIME)
			functionExpression.addRelatedExpression(expression);
	}
	/**
	 * Removes related expression.
	 *
	 * @param      expression          the related expression
	 */
	void removeRelatedExpression(Expression expression) {
		if (functionBodyType == Function.BODY_RUNTIME)
			functionExpression.removeRelatedExpression(expression);
	}
	/**
	 * Set expression modified flags in the related expressions.
	 */
	void setExpressionModifiedFlags() {
		if (functionBodyType == Function.BODY_RUNTIME)
			functionExpression.setExpressionModifiedFlag();
	}
	Function cloneForThreadSafeInternal(CloneCache cloneCache) {
		Function functionClone = cloneCache.getFunctionClone(this);
		if (functionClone == null) {
			cloneCache.cacheCloneInProgress(this);
			functionClone = new Function(this, true, cloneCache);
			cloneCache.clearCloneInProgress(this);
			cloneCache.cacheFunctionClone(this, functionClone);
		}
		return functionClone;
	}
	Function cloneForThreadSafeInternal(Expression relatedExpressionThatInitiatedClone, CloneCache cloneCache) {
		Function functionClone = cloneForThreadSafeInternal(cloneCache);
		functionClone.addRelatedExpression(relatedExpressionThatInitiatedClone);
		return functionClone;
	}
	/**
	 * Creates a completely independent 1-1 clone that can be safely used
	 * by a separate thread. If the cloned element contains references
	 * to other elements (e.g. arguments, functions, constants),
	 * then they will also be cloned and the newly created element will
	 * contain references to the corresponding clones.
	 * Important - the API allows you to extract all these clones.
	 *
	 * @return Cloned object.
	 */
	public Function cloneForThreadSafe() {
		CloneCache cloneCache = new CloneCache();
		Function functionClone = cloneForThreadSafeInternal(cloneCache);
		cloneCache.addAllAtTheEndElements();
		cloneCache.clearCache();
		return functionClone;
	}
}