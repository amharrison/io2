package org.jactr.io2.jactr.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.jactr.io2.jactr.services.ModelFragmentGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalModelFragmentParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_CODE", "RULE_DIGITS", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'namespace'", "'{'", "'}'", "'import'", "'module'", "'extension'", "'parameters'", "'('", "','", "')'", "'chunktype'", "'extends'", "'.*'", "'.'", "'buffers'", "'production'", "'?'", "'proxy'", "'script'", "'+'", "'-'", "'output'", "'isa'", "'='", "'or'", "'and'", "'not'", "':'", "'['", "']'", "'null'", "'true'", "'false'", "'@Override'", "'<'", "'<='", "'>'", "'>='", "'!='"
    };
    public static final int T__50=50;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__51=51;
    public static final int RULE_ID=5;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=8;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=4;
    public static final int RULE_DIGITS=7;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=11;
    public static final int RULE_ANY_OTHER=12;
    public static final int RULE_CODE=6;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalModelFragmentParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalModelFragmentParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalModelFragmentParser.tokenNames; }
    public String getGrammarFileName() { return "InternalModelFragment.g"; }



     	private ModelFragmentGrammarAccess grammarAccess;

        public InternalModelFragmentParser(TokenStream input, ModelFragmentGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "ModelFragment";
       	}

       	@Override
       	protected ModelFragmentGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModelFragment"
    // InternalModelFragment.g:65:1: entryRuleModelFragment returns [EObject current=null] : iv_ruleModelFragment= ruleModelFragment EOF ;
    public final EObject entryRuleModelFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModelFragment = null;


        try {
            // InternalModelFragment.g:65:54: (iv_ruleModelFragment= ruleModelFragment EOF )
            // InternalModelFragment.g:66:2: iv_ruleModelFragment= ruleModelFragment EOF
            {
             newCompositeNode(grammarAccess.getModelFragmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModelFragment=ruleModelFragment();

            state._fsp--;

             current =iv_ruleModelFragment; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModelFragment"


    // $ANTLR start "ruleModelFragment"
    // InternalModelFragment.g:72:1: ruleModelFragment returns [EObject current=null] : ( (lv_package_0_0= rulePackageDeclaration ) ) ;
    public final EObject ruleModelFragment() throws RecognitionException {
        EObject current = null;

        EObject lv_package_0_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:78:2: ( ( (lv_package_0_0= rulePackageDeclaration ) ) )
            // InternalModelFragment.g:79:2: ( (lv_package_0_0= rulePackageDeclaration ) )
            {
            // InternalModelFragment.g:79:2: ( (lv_package_0_0= rulePackageDeclaration ) )
            // InternalModelFragment.g:80:3: (lv_package_0_0= rulePackageDeclaration )
            {
            // InternalModelFragment.g:80:3: (lv_package_0_0= rulePackageDeclaration )
            // InternalModelFragment.g:81:4: lv_package_0_0= rulePackageDeclaration
            {

            				newCompositeNode(grammarAccess.getModelFragmentAccess().getPackagePackageDeclarationParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_package_0_0=rulePackageDeclaration();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getModelFragmentRule());
            				}
            				set(
            					current,
            					"package",
            					lv_package_0_0,
            					"org.jactr.io2.jactr.ModelFragment.PackageDeclaration");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModelFragment"


    // $ANTLR start "entryRulePackageDeclaration"
    // InternalModelFragment.g:101:1: entryRulePackageDeclaration returns [EObject current=null] : iv_rulePackageDeclaration= rulePackageDeclaration EOF ;
    public final EObject entryRulePackageDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePackageDeclaration = null;


        try {
            // InternalModelFragment.g:101:59: (iv_rulePackageDeclaration= rulePackageDeclaration EOF )
            // InternalModelFragment.g:102:2: iv_rulePackageDeclaration= rulePackageDeclaration EOF
            {
             newCompositeNode(grammarAccess.getPackageDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePackageDeclaration=rulePackageDeclaration();

            state._fsp--;

             current =iv_rulePackageDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePackageDeclaration"


    // $ANTLR start "rulePackageDeclaration"
    // InternalModelFragment.g:108:1: rulePackageDeclaration returns [EObject current=null] : (otherlv_0= 'namespace' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= '{' ( (lv_imports_3_0= ruleImport ) )* ( (lv_modules_4_0= ruleModelModule ) )* ( (lv_extensions_5_0= ruleModelExtension ) )* ( (lv_buffers_6_0= ruleBuffers ) )? ( (lv_elements_7_0= ruleElement ) )* otherlv_8= '}' ( (lv_parameters_9_0= ruleParametersBlock ) )? ) ;
    public final EObject rulePackageDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_8=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_imports_3_0 = null;

        EObject lv_modules_4_0 = null;

        EObject lv_extensions_5_0 = null;

        EObject lv_buffers_6_0 = null;

        EObject lv_elements_7_0 = null;

        EObject lv_parameters_9_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:114:2: ( (otherlv_0= 'namespace' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= '{' ( (lv_imports_3_0= ruleImport ) )* ( (lv_modules_4_0= ruleModelModule ) )* ( (lv_extensions_5_0= ruleModelExtension ) )* ( (lv_buffers_6_0= ruleBuffers ) )? ( (lv_elements_7_0= ruleElement ) )* otherlv_8= '}' ( (lv_parameters_9_0= ruleParametersBlock ) )? ) )
            // InternalModelFragment.g:115:2: (otherlv_0= 'namespace' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= '{' ( (lv_imports_3_0= ruleImport ) )* ( (lv_modules_4_0= ruleModelModule ) )* ( (lv_extensions_5_0= ruleModelExtension ) )* ( (lv_buffers_6_0= ruleBuffers ) )? ( (lv_elements_7_0= ruleElement ) )* otherlv_8= '}' ( (lv_parameters_9_0= ruleParametersBlock ) )? )
            {
            // InternalModelFragment.g:115:2: (otherlv_0= 'namespace' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= '{' ( (lv_imports_3_0= ruleImport ) )* ( (lv_modules_4_0= ruleModelModule ) )* ( (lv_extensions_5_0= ruleModelExtension ) )* ( (lv_buffers_6_0= ruleBuffers ) )? ( (lv_elements_7_0= ruleElement ) )* otherlv_8= '}' ( (lv_parameters_9_0= ruleParametersBlock ) )? )
            // InternalModelFragment.g:116:3: otherlv_0= 'namespace' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= '{' ( (lv_imports_3_0= ruleImport ) )* ( (lv_modules_4_0= ruleModelModule ) )* ( (lv_extensions_5_0= ruleModelExtension ) )* ( (lv_buffers_6_0= ruleBuffers ) )? ( (lv_elements_7_0= ruleElement ) )* otherlv_8= '}' ( (lv_parameters_9_0= ruleParametersBlock ) )?
            {
            otherlv_0=(Token)match(input,13,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getPackageDeclarationAccess().getNamespaceKeyword_0());
            		
            // InternalModelFragment.g:120:3: ( (lv_name_1_0= ruleQualifiedName ) )
            // InternalModelFragment.g:121:4: (lv_name_1_0= ruleQualifiedName )
            {
            // InternalModelFragment.g:121:4: (lv_name_1_0= ruleQualifiedName )
            // InternalModelFragment.g:122:5: lv_name_1_0= ruleQualifiedName
            {

            					newCompositeNode(grammarAccess.getPackageDeclarationAccess().getNameQualifiedNameParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_1_0=ruleQualifiedName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPackageDeclarationRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.jactr.io2.jactr.ModelFragment.QualifiedName");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,14,FOLLOW_5); 

            			newLeafNode(otherlv_2, grammarAccess.getPackageDeclarationAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalModelFragment.g:143:3: ( (lv_imports_3_0= ruleImport ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==16) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalModelFragment.g:144:4: (lv_imports_3_0= ruleImport )
            	    {
            	    // InternalModelFragment.g:144:4: (lv_imports_3_0= ruleImport )
            	    // InternalModelFragment.g:145:5: lv_imports_3_0= ruleImport
            	    {

            	    					newCompositeNode(grammarAccess.getPackageDeclarationAccess().getImportsImportParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_5);
            	    lv_imports_3_0=ruleImport();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getPackageDeclarationRule());
            	    					}
            	    					add(
            	    						current,
            	    						"imports",
            	    						lv_imports_3_0,
            	    						"org.jactr.io2.jactr.ModelFragment.Import");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalModelFragment.g:162:3: ( (lv_modules_4_0= ruleModelModule ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==17) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalModelFragment.g:163:4: (lv_modules_4_0= ruleModelModule )
            	    {
            	    // InternalModelFragment.g:163:4: (lv_modules_4_0= ruleModelModule )
            	    // InternalModelFragment.g:164:5: lv_modules_4_0= ruleModelModule
            	    {

            	    					newCompositeNode(grammarAccess.getPackageDeclarationAccess().getModulesModelModuleParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_6);
            	    lv_modules_4_0=ruleModelModule();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getPackageDeclarationRule());
            	    					}
            	    					add(
            	    						current,
            	    						"modules",
            	    						lv_modules_4_0,
            	    						"org.jactr.io2.jactr.ModelFragment.ModelModule");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // InternalModelFragment.g:181:3: ( (lv_extensions_5_0= ruleModelExtension ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==18) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalModelFragment.g:182:4: (lv_extensions_5_0= ruleModelExtension )
            	    {
            	    // InternalModelFragment.g:182:4: (lv_extensions_5_0= ruleModelExtension )
            	    // InternalModelFragment.g:183:5: lv_extensions_5_0= ruleModelExtension
            	    {

            	    					newCompositeNode(grammarAccess.getPackageDeclarationAccess().getExtensionsModelExtensionParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_7);
            	    lv_extensions_5_0=ruleModelExtension();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getPackageDeclarationRule());
            	    					}
            	    					add(
            	    						current,
            	    						"extensions",
            	    						lv_extensions_5_0,
            	    						"org.jactr.io2.jactr.ModelFragment.ModelExtension");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // InternalModelFragment.g:200:3: ( (lv_buffers_6_0= ruleBuffers ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==27) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalModelFragment.g:201:4: (lv_buffers_6_0= ruleBuffers )
                    {
                    // InternalModelFragment.g:201:4: (lv_buffers_6_0= ruleBuffers )
                    // InternalModelFragment.g:202:5: lv_buffers_6_0= ruleBuffers
                    {

                    					newCompositeNode(grammarAccess.getPackageDeclarationAccess().getBuffersBuffersParserRuleCall_6_0());
                    				
                    pushFollow(FOLLOW_8);
                    lv_buffers_6_0=ruleBuffers();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getPackageDeclarationRule());
                    					}
                    					set(
                    						current,
                    						"buffers",
                    						lv_buffers_6_0,
                    						"org.jactr.io2.jactr.ModelFragment.Buffers");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalModelFragment.g:219:3: ( (lv_elements_7_0= ruleElement ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==RULE_ID||LA5_0==19||LA5_0==23||LA5_0==28||LA5_0==46) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalModelFragment.g:220:4: (lv_elements_7_0= ruleElement )
            	    {
            	    // InternalModelFragment.g:220:4: (lv_elements_7_0= ruleElement )
            	    // InternalModelFragment.g:221:5: lv_elements_7_0= ruleElement
            	    {

            	    					newCompositeNode(grammarAccess.getPackageDeclarationAccess().getElementsElementParserRuleCall_7_0());
            	    				
            	    pushFollow(FOLLOW_8);
            	    lv_elements_7_0=ruleElement();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getPackageDeclarationRule());
            	    					}
            	    					add(
            	    						current,
            	    						"elements",
            	    						lv_elements_7_0,
            	    						"org.jactr.io2.jactr.ModelFragment.Element");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            otherlv_8=(Token)match(input,15,FOLLOW_9); 

            			newLeafNode(otherlv_8, grammarAccess.getPackageDeclarationAccess().getRightCurlyBracketKeyword_8());
            		
            // InternalModelFragment.g:242:3: ( (lv_parameters_9_0= ruleParametersBlock ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==41) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalModelFragment.g:243:4: (lv_parameters_9_0= ruleParametersBlock )
                    {
                    // InternalModelFragment.g:243:4: (lv_parameters_9_0= ruleParametersBlock )
                    // InternalModelFragment.g:244:5: lv_parameters_9_0= ruleParametersBlock
                    {

                    					newCompositeNode(grammarAccess.getPackageDeclarationAccess().getParametersParametersBlockParserRuleCall_9_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_parameters_9_0=ruleParametersBlock();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getPackageDeclarationRule());
                    					}
                    					set(
                    						current,
                    						"parameters",
                    						lv_parameters_9_0,
                    						"org.jactr.io2.jactr.ModelFragment.ParametersBlock");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePackageDeclaration"


    // $ANTLR start "entryRuleImport"
    // InternalModelFragment.g:265:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // InternalModelFragment.g:265:47: (iv_ruleImport= ruleImport EOF )
            // InternalModelFragment.g:266:2: iv_ruleImport= ruleImport EOF
            {
             newCompositeNode(grammarAccess.getImportRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleImport=ruleImport();

            state._fsp--;

             current =iv_ruleImport; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleImport"


    // $ANTLR start "ruleImport"
    // InternalModelFragment.g:272:1: ruleImport returns [EObject current=null] : (otherlv_0= 'import' ( (lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard ) ) ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_importedNamespace_1_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:278:2: ( (otherlv_0= 'import' ( (lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard ) ) ) )
            // InternalModelFragment.g:279:2: (otherlv_0= 'import' ( (lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard ) ) )
            {
            // InternalModelFragment.g:279:2: (otherlv_0= 'import' ( (lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard ) ) )
            // InternalModelFragment.g:280:3: otherlv_0= 'import' ( (lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard ) )
            {
            otherlv_0=(Token)match(input,16,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
            		
            // InternalModelFragment.g:284:3: ( (lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard ) )
            // InternalModelFragment.g:285:4: (lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard )
            {
            // InternalModelFragment.g:285:4: (lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard )
            // InternalModelFragment.g:286:5: lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard
            {

            					newCompositeNode(grammarAccess.getImportAccess().getImportedNamespaceQualifiedNameWithWildcardParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_importedNamespace_1_0=ruleQualifiedNameWithWildcard();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getImportRule());
            					}
            					set(
            						current,
            						"importedNamespace",
            						lv_importedNamespace_1_0,
            						"org.jactr.io2.jactr.ModelFragment.QualifiedNameWithWildcard");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleImport"


    // $ANTLR start "entryRuleModelModule"
    // InternalModelFragment.g:307:1: entryRuleModelModule returns [EObject current=null] : iv_ruleModelModule= ruleModelModule EOF ;
    public final EObject entryRuleModelModule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModelModule = null;


        try {
            // InternalModelFragment.g:307:52: (iv_ruleModelModule= ruleModelModule EOF )
            // InternalModelFragment.g:308:2: iv_ruleModelModule= ruleModelModule EOF
            {
             newCompositeNode(grammarAccess.getModelModuleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModelModule=ruleModelModule();

            state._fsp--;

             current =iv_ruleModelModule; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModelModule"


    // $ANTLR start "ruleModelModule"
    // InternalModelFragment.g:314:1: ruleModelModule returns [EObject current=null] : (otherlv_0= 'module' ( (lv_moduleClass_1_0= RULE_STRING ) ) ( (lv_parameters_2_0= ruleParametersBlock ) )? ) ;
    public final EObject ruleModelModule() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_moduleClass_1_0=null;
        EObject lv_parameters_2_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:320:2: ( (otherlv_0= 'module' ( (lv_moduleClass_1_0= RULE_STRING ) ) ( (lv_parameters_2_0= ruleParametersBlock ) )? ) )
            // InternalModelFragment.g:321:2: (otherlv_0= 'module' ( (lv_moduleClass_1_0= RULE_STRING ) ) ( (lv_parameters_2_0= ruleParametersBlock ) )? )
            {
            // InternalModelFragment.g:321:2: (otherlv_0= 'module' ( (lv_moduleClass_1_0= RULE_STRING ) ) ( (lv_parameters_2_0= ruleParametersBlock ) )? )
            // InternalModelFragment.g:322:3: otherlv_0= 'module' ( (lv_moduleClass_1_0= RULE_STRING ) ) ( (lv_parameters_2_0= ruleParametersBlock ) )?
            {
            otherlv_0=(Token)match(input,17,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getModelModuleAccess().getModuleKeyword_0());
            		
            // InternalModelFragment.g:326:3: ( (lv_moduleClass_1_0= RULE_STRING ) )
            // InternalModelFragment.g:327:4: (lv_moduleClass_1_0= RULE_STRING )
            {
            // InternalModelFragment.g:327:4: (lv_moduleClass_1_0= RULE_STRING )
            // InternalModelFragment.g:328:5: lv_moduleClass_1_0= RULE_STRING
            {
            lv_moduleClass_1_0=(Token)match(input,RULE_STRING,FOLLOW_9); 

            					newLeafNode(lv_moduleClass_1_0, grammarAccess.getModelModuleAccess().getModuleClassSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getModelModuleRule());
            					}
            					setWithLastConsumed(
            						current,
            						"moduleClass",
            						lv_moduleClass_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalModelFragment.g:344:3: ( (lv_parameters_2_0= ruleParametersBlock ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==41) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalModelFragment.g:345:4: (lv_parameters_2_0= ruleParametersBlock )
                    {
                    // InternalModelFragment.g:345:4: (lv_parameters_2_0= ruleParametersBlock )
                    // InternalModelFragment.g:346:5: lv_parameters_2_0= ruleParametersBlock
                    {

                    					newCompositeNode(grammarAccess.getModelModuleAccess().getParametersParametersBlockParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_parameters_2_0=ruleParametersBlock();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getModelModuleRule());
                    					}
                    					set(
                    						current,
                    						"parameters",
                    						lv_parameters_2_0,
                    						"org.jactr.io2.jactr.ModelFragment.ParametersBlock");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModelModule"


    // $ANTLR start "entryRuleModelExtension"
    // InternalModelFragment.g:367:1: entryRuleModelExtension returns [EObject current=null] : iv_ruleModelExtension= ruleModelExtension EOF ;
    public final EObject entryRuleModelExtension() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModelExtension = null;


        try {
            // InternalModelFragment.g:367:55: (iv_ruleModelExtension= ruleModelExtension EOF )
            // InternalModelFragment.g:368:2: iv_ruleModelExtension= ruleModelExtension EOF
            {
             newCompositeNode(grammarAccess.getModelExtensionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModelExtension=ruleModelExtension();

            state._fsp--;

             current =iv_ruleModelExtension; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModelExtension"


    // $ANTLR start "ruleModelExtension"
    // InternalModelFragment.g:374:1: ruleModelExtension returns [EObject current=null] : (otherlv_0= 'extension' ( (lv_extensionClass_1_0= RULE_STRING ) ) ( (lv_parameters_2_0= ruleParametersBlock ) )? ) ;
    public final EObject ruleModelExtension() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_extensionClass_1_0=null;
        EObject lv_parameters_2_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:380:2: ( (otherlv_0= 'extension' ( (lv_extensionClass_1_0= RULE_STRING ) ) ( (lv_parameters_2_0= ruleParametersBlock ) )? ) )
            // InternalModelFragment.g:381:2: (otherlv_0= 'extension' ( (lv_extensionClass_1_0= RULE_STRING ) ) ( (lv_parameters_2_0= ruleParametersBlock ) )? )
            {
            // InternalModelFragment.g:381:2: (otherlv_0= 'extension' ( (lv_extensionClass_1_0= RULE_STRING ) ) ( (lv_parameters_2_0= ruleParametersBlock ) )? )
            // InternalModelFragment.g:382:3: otherlv_0= 'extension' ( (lv_extensionClass_1_0= RULE_STRING ) ) ( (lv_parameters_2_0= ruleParametersBlock ) )?
            {
            otherlv_0=(Token)match(input,18,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getModelExtensionAccess().getExtensionKeyword_0());
            		
            // InternalModelFragment.g:386:3: ( (lv_extensionClass_1_0= RULE_STRING ) )
            // InternalModelFragment.g:387:4: (lv_extensionClass_1_0= RULE_STRING )
            {
            // InternalModelFragment.g:387:4: (lv_extensionClass_1_0= RULE_STRING )
            // InternalModelFragment.g:388:5: lv_extensionClass_1_0= RULE_STRING
            {
            lv_extensionClass_1_0=(Token)match(input,RULE_STRING,FOLLOW_9); 

            					newLeafNode(lv_extensionClass_1_0, grammarAccess.getModelExtensionAccess().getExtensionClassSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getModelExtensionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"extensionClass",
            						lv_extensionClass_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalModelFragment.g:404:3: ( (lv_parameters_2_0= ruleParametersBlock ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==41) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalModelFragment.g:405:4: (lv_parameters_2_0= ruleParametersBlock )
                    {
                    // InternalModelFragment.g:405:4: (lv_parameters_2_0= ruleParametersBlock )
                    // InternalModelFragment.g:406:5: lv_parameters_2_0= ruleParametersBlock
                    {

                    					newCompositeNode(grammarAccess.getModelExtensionAccess().getParametersParametersBlockParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_parameters_2_0=ruleParametersBlock();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getModelExtensionRule());
                    					}
                    					set(
                    						current,
                    						"parameters",
                    						lv_parameters_2_0,
                    						"org.jactr.io2.jactr.ModelFragment.ParametersBlock");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModelExtension"


    // $ANTLR start "entryRuleElement"
    // InternalModelFragment.g:427:1: entryRuleElement returns [EObject current=null] : iv_ruleElement= ruleElement EOF ;
    public final EObject entryRuleElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElement = null;


        try {
            // InternalModelFragment.g:427:48: (iv_ruleElement= ruleElement EOF )
            // InternalModelFragment.g:428:2: iv_ruleElement= ruleElement EOF
            {
             newCompositeNode(grammarAccess.getElementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElement=ruleElement();

            state._fsp--;

             current =iv_ruleElement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleElement"


    // $ANTLR start "ruleElement"
    // InternalModelFragment.g:434:1: ruleElement returns [EObject current=null] : (this_ChunkType_0= ruleChunkType | this_Chunks_1= ruleChunks | this_Production_2= ruleProduction | this_Parameters_3= ruleParameters ) ;
    public final EObject ruleElement() throws RecognitionException {
        EObject current = null;

        EObject this_ChunkType_0 = null;

        EObject this_Chunks_1 = null;

        EObject this_Production_2 = null;

        EObject this_Parameters_3 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:440:2: ( (this_ChunkType_0= ruleChunkType | this_Chunks_1= ruleChunks | this_Production_2= ruleProduction | this_Parameters_3= ruleParameters ) )
            // InternalModelFragment.g:441:2: (this_ChunkType_0= ruleChunkType | this_Chunks_1= ruleChunks | this_Production_2= ruleProduction | this_Parameters_3= ruleParameters )
            {
            // InternalModelFragment.g:441:2: (this_ChunkType_0= ruleChunkType | this_Chunks_1= ruleChunks | this_Production_2= ruleProduction | this_Parameters_3= ruleParameters )
            int alt9=4;
            switch ( input.LA(1) ) {
            case 46:
                {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==23) ) {
                    alt9=1;
                }
                else if ( (LA9_1==28) ) {
                    alt9=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    throw nvae;
                }
                }
                break;
            case 23:
                {
                alt9=1;
                }
                break;
            case RULE_ID:
                {
                alt9=2;
                }
                break;
            case 28:
                {
                alt9=3;
                }
                break;
            case 19:
                {
                alt9=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // InternalModelFragment.g:442:3: this_ChunkType_0= ruleChunkType
                    {

                    			newCompositeNode(grammarAccess.getElementAccess().getChunkTypeParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_ChunkType_0=ruleChunkType();

                    state._fsp--;


                    			current = this_ChunkType_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalModelFragment.g:451:3: this_Chunks_1= ruleChunks
                    {

                    			newCompositeNode(grammarAccess.getElementAccess().getChunksParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Chunks_1=ruleChunks();

                    state._fsp--;


                    			current = this_Chunks_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalModelFragment.g:460:3: this_Production_2= ruleProduction
                    {

                    			newCompositeNode(grammarAccess.getElementAccess().getProductionParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_Production_2=ruleProduction();

                    state._fsp--;


                    			current = this_Production_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalModelFragment.g:469:3: this_Parameters_3= ruleParameters
                    {

                    			newCompositeNode(grammarAccess.getElementAccess().getParametersParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_Parameters_3=ruleParameters();

                    state._fsp--;


                    			current = this_Parameters_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleElement"


    // $ANTLR start "entryRuleParameters"
    // InternalModelFragment.g:481:1: entryRuleParameters returns [EObject current=null] : iv_ruleParameters= ruleParameters EOF ;
    public final EObject entryRuleParameters() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameters = null;


        try {
            // InternalModelFragment.g:481:51: (iv_ruleParameters= ruleParameters EOF )
            // InternalModelFragment.g:482:2: iv_ruleParameters= ruleParameters EOF
            {
             newCompositeNode(grammarAccess.getParametersRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParameters=ruleParameters();

            state._fsp--;

             current =iv_ruleParameters; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameters"


    // $ANTLR start "ruleParameters"
    // InternalModelFragment.g:488:1: ruleParameters returns [EObject current=null] : (otherlv_0= 'parameters' otherlv_1= '(' ( (lv_elements_2_0= RULE_ID ) ) (otherlv_3= ',' ( (lv_elements_4_0= RULE_ID ) ) )* otherlv_5= ')' ( (lv_parameters_6_0= ruleParametersBlock ) ) ) ;
    public final EObject ruleParameters() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_elements_2_0=null;
        Token otherlv_3=null;
        Token lv_elements_4_0=null;
        Token otherlv_5=null;
        EObject lv_parameters_6_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:494:2: ( (otherlv_0= 'parameters' otherlv_1= '(' ( (lv_elements_2_0= RULE_ID ) ) (otherlv_3= ',' ( (lv_elements_4_0= RULE_ID ) ) )* otherlv_5= ')' ( (lv_parameters_6_0= ruleParametersBlock ) ) ) )
            // InternalModelFragment.g:495:2: (otherlv_0= 'parameters' otherlv_1= '(' ( (lv_elements_2_0= RULE_ID ) ) (otherlv_3= ',' ( (lv_elements_4_0= RULE_ID ) ) )* otherlv_5= ')' ( (lv_parameters_6_0= ruleParametersBlock ) ) )
            {
            // InternalModelFragment.g:495:2: (otherlv_0= 'parameters' otherlv_1= '(' ( (lv_elements_2_0= RULE_ID ) ) (otherlv_3= ',' ( (lv_elements_4_0= RULE_ID ) ) )* otherlv_5= ')' ( (lv_parameters_6_0= ruleParametersBlock ) ) )
            // InternalModelFragment.g:496:3: otherlv_0= 'parameters' otherlv_1= '(' ( (lv_elements_2_0= RULE_ID ) ) (otherlv_3= ',' ( (lv_elements_4_0= RULE_ID ) ) )* otherlv_5= ')' ( (lv_parameters_6_0= ruleParametersBlock ) )
            {
            otherlv_0=(Token)match(input,19,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getParametersAccess().getParametersKeyword_0());
            		
            otherlv_1=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getParametersAccess().getLeftParenthesisKeyword_1());
            		
            // InternalModelFragment.g:504:3: ( (lv_elements_2_0= RULE_ID ) )
            // InternalModelFragment.g:505:4: (lv_elements_2_0= RULE_ID )
            {
            // InternalModelFragment.g:505:4: (lv_elements_2_0= RULE_ID )
            // InternalModelFragment.g:506:5: lv_elements_2_0= RULE_ID
            {
            lv_elements_2_0=(Token)match(input,RULE_ID,FOLLOW_12); 

            					newLeafNode(lv_elements_2_0, grammarAccess.getParametersAccess().getElementsIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getParametersRule());
            					}
            					addWithLastConsumed(
            						current,
            						"elements",
            						lv_elements_2_0,
            						"org.jactr.io2.jactr.ModelFragment.ID");
            				

            }


            }

            // InternalModelFragment.g:522:3: (otherlv_3= ',' ( (lv_elements_4_0= RULE_ID ) ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==21) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalModelFragment.g:523:4: otherlv_3= ',' ( (lv_elements_4_0= RULE_ID ) )
            	    {
            	    otherlv_3=(Token)match(input,21,FOLLOW_3); 

            	    				newLeafNode(otherlv_3, grammarAccess.getParametersAccess().getCommaKeyword_3_0());
            	    			
            	    // InternalModelFragment.g:527:4: ( (lv_elements_4_0= RULE_ID ) )
            	    // InternalModelFragment.g:528:5: (lv_elements_4_0= RULE_ID )
            	    {
            	    // InternalModelFragment.g:528:5: (lv_elements_4_0= RULE_ID )
            	    // InternalModelFragment.g:529:6: lv_elements_4_0= RULE_ID
            	    {
            	    lv_elements_4_0=(Token)match(input,RULE_ID,FOLLOW_12); 

            	    						newLeafNode(lv_elements_4_0, grammarAccess.getParametersAccess().getElementsIDTerminalRuleCall_3_1_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getParametersRule());
            	    						}
            	    						addWithLastConsumed(
            	    							current,
            	    							"elements",
            	    							lv_elements_4_0,
            	    							"org.jactr.io2.jactr.ModelFragment.ID");
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            otherlv_5=(Token)match(input,22,FOLLOW_13); 

            			newLeafNode(otherlv_5, grammarAccess.getParametersAccess().getRightParenthesisKeyword_4());
            		
            // InternalModelFragment.g:550:3: ( (lv_parameters_6_0= ruleParametersBlock ) )
            // InternalModelFragment.g:551:4: (lv_parameters_6_0= ruleParametersBlock )
            {
            // InternalModelFragment.g:551:4: (lv_parameters_6_0= ruleParametersBlock )
            // InternalModelFragment.g:552:5: lv_parameters_6_0= ruleParametersBlock
            {

            					newCompositeNode(grammarAccess.getParametersAccess().getParametersParametersBlockParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_2);
            lv_parameters_6_0=ruleParametersBlock();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getParametersRule());
            					}
            					set(
            						current,
            						"parameters",
            						lv_parameters_6_0,
            						"org.jactr.io2.jactr.ModelFragment.ParametersBlock");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameters"


    // $ANTLR start "entryRuleChunks"
    // InternalModelFragment.g:573:1: entryRuleChunks returns [EObject current=null] : iv_ruleChunks= ruleChunks EOF ;
    public final EObject entryRuleChunks() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChunks = null;


        try {
            // InternalModelFragment.g:573:47: (iv_ruleChunks= ruleChunks EOF )
            // InternalModelFragment.g:574:2: iv_ruleChunks= ruleChunks EOF
            {
             newCompositeNode(grammarAccess.getChunksRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleChunks=ruleChunks();

            state._fsp--;

             current =iv_ruleChunks; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleChunks"


    // $ANTLR start "ruleChunks"
    // InternalModelFragment.g:580:1: ruleChunks returns [EObject current=null] : ( ( ( ruleQualifiedName ) ) ( (lv_chunks_1_0= ruleChunkDef ) ) (otherlv_2= ',' ( (lv_chunks_3_0= ruleChunkDef ) ) )* ) ;
    public final EObject ruleChunks() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_chunks_1_0 = null;

        EObject lv_chunks_3_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:586:2: ( ( ( ( ruleQualifiedName ) ) ( (lv_chunks_1_0= ruleChunkDef ) ) (otherlv_2= ',' ( (lv_chunks_3_0= ruleChunkDef ) ) )* ) )
            // InternalModelFragment.g:587:2: ( ( ( ruleQualifiedName ) ) ( (lv_chunks_1_0= ruleChunkDef ) ) (otherlv_2= ',' ( (lv_chunks_3_0= ruleChunkDef ) ) )* )
            {
            // InternalModelFragment.g:587:2: ( ( ( ruleQualifiedName ) ) ( (lv_chunks_1_0= ruleChunkDef ) ) (otherlv_2= ',' ( (lv_chunks_3_0= ruleChunkDef ) ) )* )
            // InternalModelFragment.g:588:3: ( ( ruleQualifiedName ) ) ( (lv_chunks_1_0= ruleChunkDef ) ) (otherlv_2= ',' ( (lv_chunks_3_0= ruleChunkDef ) ) )*
            {
            // InternalModelFragment.g:588:3: ( ( ruleQualifiedName ) )
            // InternalModelFragment.g:589:4: ( ruleQualifiedName )
            {
            // InternalModelFragment.g:589:4: ( ruleQualifiedName )
            // InternalModelFragment.g:590:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getChunksRule());
            					}
            				

            					newCompositeNode(grammarAccess.getChunksAccess().getTypeChunkTypeCrossReference_0_0());
            				
            pushFollow(FOLLOW_14);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalModelFragment.g:604:3: ( (lv_chunks_1_0= ruleChunkDef ) )
            // InternalModelFragment.g:605:4: (lv_chunks_1_0= ruleChunkDef )
            {
            // InternalModelFragment.g:605:4: (lv_chunks_1_0= ruleChunkDef )
            // InternalModelFragment.g:606:5: lv_chunks_1_0= ruleChunkDef
            {

            					newCompositeNode(grammarAccess.getChunksAccess().getChunksChunkDefParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_15);
            lv_chunks_1_0=ruleChunkDef();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getChunksRule());
            					}
            					add(
            						current,
            						"chunks",
            						lv_chunks_1_0,
            						"org.jactr.io2.jactr.ModelFragment.ChunkDef");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalModelFragment.g:623:3: (otherlv_2= ',' ( (lv_chunks_3_0= ruleChunkDef ) ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==21) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalModelFragment.g:624:4: otherlv_2= ',' ( (lv_chunks_3_0= ruleChunkDef ) )
            	    {
            	    otherlv_2=(Token)match(input,21,FOLLOW_14); 

            	    				newLeafNode(otherlv_2, grammarAccess.getChunksAccess().getCommaKeyword_2_0());
            	    			
            	    // InternalModelFragment.g:628:4: ( (lv_chunks_3_0= ruleChunkDef ) )
            	    // InternalModelFragment.g:629:5: (lv_chunks_3_0= ruleChunkDef )
            	    {
            	    // InternalModelFragment.g:629:5: (lv_chunks_3_0= ruleChunkDef )
            	    // InternalModelFragment.g:630:6: lv_chunks_3_0= ruleChunkDef
            	    {

            	    						newCompositeNode(grammarAccess.getChunksAccess().getChunksChunkDefParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_15);
            	    lv_chunks_3_0=ruleChunkDef();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getChunksRule());
            	    						}
            	    						add(
            	    							current,
            	    							"chunks",
            	    							lv_chunks_3_0,
            	    							"org.jactr.io2.jactr.ModelFragment.ChunkDef");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleChunks"


    // $ANTLR start "entryRuleChunkDef"
    // InternalModelFragment.g:652:1: entryRuleChunkDef returns [EObject current=null] : iv_ruleChunkDef= ruleChunkDef EOF ;
    public final EObject entryRuleChunkDef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChunkDef = null;


        try {
            // InternalModelFragment.g:652:49: (iv_ruleChunkDef= ruleChunkDef EOF )
            // InternalModelFragment.g:653:2: iv_ruleChunkDef= ruleChunkDef EOF
            {
             newCompositeNode(grammarAccess.getChunkDefRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleChunkDef=ruleChunkDef();

            state._fsp--;

             current =iv_ruleChunkDef; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleChunkDef"


    // $ANTLR start "ruleChunkDef"
    // InternalModelFragment.g:659:1: ruleChunkDef returns [EObject current=null] : (this_Overridable_0= ruleOverridable[$current] ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= '(' ( (lv_slots_3_0= ruleSimpleSlot ) ) (otherlv_4= ',' ( (lv_slots_5_0= ruleSimpleSlot ) ) )* otherlv_6= ')' )? ( (lv_parameters_7_0= ruleParametersBlock ) )? ) ;
    public final EObject ruleChunkDef() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject this_Overridable_0 = null;

        EObject lv_slots_3_0 = null;

        EObject lv_slots_5_0 = null;

        EObject lv_parameters_7_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:665:2: ( (this_Overridable_0= ruleOverridable[$current] ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= '(' ( (lv_slots_3_0= ruleSimpleSlot ) ) (otherlv_4= ',' ( (lv_slots_5_0= ruleSimpleSlot ) ) )* otherlv_6= ')' )? ( (lv_parameters_7_0= ruleParametersBlock ) )? ) )
            // InternalModelFragment.g:666:2: (this_Overridable_0= ruleOverridable[$current] ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= '(' ( (lv_slots_3_0= ruleSimpleSlot ) ) (otherlv_4= ',' ( (lv_slots_5_0= ruleSimpleSlot ) ) )* otherlv_6= ')' )? ( (lv_parameters_7_0= ruleParametersBlock ) )? )
            {
            // InternalModelFragment.g:666:2: (this_Overridable_0= ruleOverridable[$current] ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= '(' ( (lv_slots_3_0= ruleSimpleSlot ) ) (otherlv_4= ',' ( (lv_slots_5_0= ruleSimpleSlot ) ) )* otherlv_6= ')' )? ( (lv_parameters_7_0= ruleParametersBlock ) )? )
            // InternalModelFragment.g:667:3: this_Overridable_0= ruleOverridable[$current] ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= '(' ( (lv_slots_3_0= ruleSimpleSlot ) ) (otherlv_4= ',' ( (lv_slots_5_0= ruleSimpleSlot ) ) )* otherlv_6= ')' )? ( (lv_parameters_7_0= ruleParametersBlock ) )?
            {

            			if (current==null) {
            				current = createModelElement(grammarAccess.getChunkDefRule());
            			}
            			newCompositeNode(grammarAccess.getChunkDefAccess().getOverridableParserRuleCall_0());
            		
            pushFollow(FOLLOW_3);
            this_Overridable_0=ruleOverridable(current);

            state._fsp--;


            			current = this_Overridable_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalModelFragment.g:678:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalModelFragment.g:679:4: (lv_name_1_0= RULE_ID )
            {
            // InternalModelFragment.g:679:4: (lv_name_1_0= RULE_ID )
            // InternalModelFragment.g:680:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_16); 

            					newLeafNode(lv_name_1_0, grammarAccess.getChunkDefAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getChunkDefRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.jactr.io2.jactr.ModelFragment.ID");
            				

            }


            }

            // InternalModelFragment.g:696:3: (otherlv_2= '(' ( (lv_slots_3_0= ruleSimpleSlot ) ) (otherlv_4= ',' ( (lv_slots_5_0= ruleSimpleSlot ) ) )* otherlv_6= ')' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==20) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalModelFragment.g:697:4: otherlv_2= '(' ( (lv_slots_3_0= ruleSimpleSlot ) ) (otherlv_4= ',' ( (lv_slots_5_0= ruleSimpleSlot ) ) )* otherlv_6= ')'
                    {
                    otherlv_2=(Token)match(input,20,FOLLOW_3); 

                    				newLeafNode(otherlv_2, grammarAccess.getChunkDefAccess().getLeftParenthesisKeyword_2_0());
                    			
                    // InternalModelFragment.g:701:4: ( (lv_slots_3_0= ruleSimpleSlot ) )
                    // InternalModelFragment.g:702:5: (lv_slots_3_0= ruleSimpleSlot )
                    {
                    // InternalModelFragment.g:702:5: (lv_slots_3_0= ruleSimpleSlot )
                    // InternalModelFragment.g:703:6: lv_slots_3_0= ruleSimpleSlot
                    {

                    						newCompositeNode(grammarAccess.getChunkDefAccess().getSlotsSimpleSlotParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_12);
                    lv_slots_3_0=ruleSimpleSlot();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getChunkDefRule());
                    						}
                    						add(
                    							current,
                    							"slots",
                    							lv_slots_3_0,
                    							"org.jactr.io2.jactr.ModelFragment.SimpleSlot");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalModelFragment.g:720:4: (otherlv_4= ',' ( (lv_slots_5_0= ruleSimpleSlot ) ) )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==21) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // InternalModelFragment.g:721:5: otherlv_4= ',' ( (lv_slots_5_0= ruleSimpleSlot ) )
                    	    {
                    	    otherlv_4=(Token)match(input,21,FOLLOW_3); 

                    	    					newLeafNode(otherlv_4, grammarAccess.getChunkDefAccess().getCommaKeyword_2_2_0());
                    	    				
                    	    // InternalModelFragment.g:725:5: ( (lv_slots_5_0= ruleSimpleSlot ) )
                    	    // InternalModelFragment.g:726:6: (lv_slots_5_0= ruleSimpleSlot )
                    	    {
                    	    // InternalModelFragment.g:726:6: (lv_slots_5_0= ruleSimpleSlot )
                    	    // InternalModelFragment.g:727:7: lv_slots_5_0= ruleSimpleSlot
                    	    {

                    	    							newCompositeNode(grammarAccess.getChunkDefAccess().getSlotsSimpleSlotParserRuleCall_2_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_12);
                    	    lv_slots_5_0=ruleSimpleSlot();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getChunkDefRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"slots",
                    	    								lv_slots_5_0,
                    	    								"org.jactr.io2.jactr.ModelFragment.SimpleSlot");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    otherlv_6=(Token)match(input,22,FOLLOW_9); 

                    				newLeafNode(otherlv_6, grammarAccess.getChunkDefAccess().getRightParenthesisKeyword_2_3());
                    			

                    }
                    break;

            }

            // InternalModelFragment.g:750:3: ( (lv_parameters_7_0= ruleParametersBlock ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==41) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalModelFragment.g:751:4: (lv_parameters_7_0= ruleParametersBlock )
                    {
                    // InternalModelFragment.g:751:4: (lv_parameters_7_0= ruleParametersBlock )
                    // InternalModelFragment.g:752:5: lv_parameters_7_0= ruleParametersBlock
                    {

                    					newCompositeNode(grammarAccess.getChunkDefAccess().getParametersParametersBlockParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_parameters_7_0=ruleParametersBlock();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getChunkDefRule());
                    					}
                    					set(
                    						current,
                    						"parameters",
                    						lv_parameters_7_0,
                    						"org.jactr.io2.jactr.ModelFragment.ParametersBlock");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleChunkDef"


    // $ANTLR start "entryRuleChunkType"
    // InternalModelFragment.g:773:1: entryRuleChunkType returns [EObject current=null] : iv_ruleChunkType= ruleChunkType EOF ;
    public final EObject entryRuleChunkType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChunkType = null;


        try {
            // InternalModelFragment.g:773:50: (iv_ruleChunkType= ruleChunkType EOF )
            // InternalModelFragment.g:774:2: iv_ruleChunkType= ruleChunkType EOF
            {
             newCompositeNode(grammarAccess.getChunkTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleChunkType=ruleChunkType();

            state._fsp--;

             current =iv_ruleChunkType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleChunkType"


    // $ANTLR start "ruleChunkType"
    // InternalModelFragment.g:780:1: ruleChunkType returns [EObject current=null] : (this_Overridable_0= ruleOverridable[$current] otherlv_1= 'chunktype' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' ( (lv_slots_6_0= ruleSimpleSlot ) )* otherlv_7= '}' ( (lv_parameters_8_0= ruleParametersBlock ) )? ) ;
    public final EObject ruleChunkType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject this_Overridable_0 = null;

        EObject lv_slots_6_0 = null;

        EObject lv_parameters_8_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:786:2: ( (this_Overridable_0= ruleOverridable[$current] otherlv_1= 'chunktype' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' ( (lv_slots_6_0= ruleSimpleSlot ) )* otherlv_7= '}' ( (lv_parameters_8_0= ruleParametersBlock ) )? ) )
            // InternalModelFragment.g:787:2: (this_Overridable_0= ruleOverridable[$current] otherlv_1= 'chunktype' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' ( (lv_slots_6_0= ruleSimpleSlot ) )* otherlv_7= '}' ( (lv_parameters_8_0= ruleParametersBlock ) )? )
            {
            // InternalModelFragment.g:787:2: (this_Overridable_0= ruleOverridable[$current] otherlv_1= 'chunktype' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' ( (lv_slots_6_0= ruleSimpleSlot ) )* otherlv_7= '}' ( (lv_parameters_8_0= ruleParametersBlock ) )? )
            // InternalModelFragment.g:788:3: this_Overridable_0= ruleOverridable[$current] otherlv_1= 'chunktype' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' ( (lv_slots_6_0= ruleSimpleSlot ) )* otherlv_7= '}' ( (lv_parameters_8_0= ruleParametersBlock ) )?
            {

            			if (current==null) {
            				current = createModelElement(grammarAccess.getChunkTypeRule());
            			}
            			newCompositeNode(grammarAccess.getChunkTypeAccess().getOverridableParserRuleCall_0());
            		
            pushFollow(FOLLOW_17);
            this_Overridable_0=ruleOverridable(current);

            state._fsp--;


            			current = this_Overridable_0;
            			afterParserOrEnumRuleCall();
            		
            otherlv_1=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getChunkTypeAccess().getChunktypeKeyword_1());
            		
            // InternalModelFragment.g:803:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalModelFragment.g:804:4: (lv_name_2_0= RULE_ID )
            {
            // InternalModelFragment.g:804:4: (lv_name_2_0= RULE_ID )
            // InternalModelFragment.g:805:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_18); 

            					newLeafNode(lv_name_2_0, grammarAccess.getChunkTypeAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getChunkTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.jactr.io2.jactr.ModelFragment.ID");
            				

            }


            }

            // InternalModelFragment.g:821:3: (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==24) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalModelFragment.g:822:4: otherlv_3= 'extends' ( ( ruleQualifiedName ) )
                    {
                    otherlv_3=(Token)match(input,24,FOLLOW_3); 

                    				newLeafNode(otherlv_3, grammarAccess.getChunkTypeAccess().getExtendsKeyword_3_0());
                    			
                    // InternalModelFragment.g:826:4: ( ( ruleQualifiedName ) )
                    // InternalModelFragment.g:827:5: ( ruleQualifiedName )
                    {
                    // InternalModelFragment.g:827:5: ( ruleQualifiedName )
                    // InternalModelFragment.g:828:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getChunkTypeRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getChunkTypeAccess().getSuperTypeChunkTypeCrossReference_3_1_0());
                    					
                    pushFollow(FOLLOW_4);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,14,FOLLOW_19); 

            			newLeafNode(otherlv_5, grammarAccess.getChunkTypeAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalModelFragment.g:847:3: ( (lv_slots_6_0= ruleSimpleSlot ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==RULE_ID) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalModelFragment.g:848:4: (lv_slots_6_0= ruleSimpleSlot )
            	    {
            	    // InternalModelFragment.g:848:4: (lv_slots_6_0= ruleSimpleSlot )
            	    // InternalModelFragment.g:849:5: lv_slots_6_0= ruleSimpleSlot
            	    {

            	    					newCompositeNode(grammarAccess.getChunkTypeAccess().getSlotsSimpleSlotParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_19);
            	    lv_slots_6_0=ruleSimpleSlot();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getChunkTypeRule());
            	    					}
            	    					add(
            	    						current,
            	    						"slots",
            	    						lv_slots_6_0,
            	    						"org.jactr.io2.jactr.ModelFragment.SimpleSlot");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            otherlv_7=(Token)match(input,15,FOLLOW_9); 

            			newLeafNode(otherlv_7, grammarAccess.getChunkTypeAccess().getRightCurlyBracketKeyword_6());
            		
            // InternalModelFragment.g:870:3: ( (lv_parameters_8_0= ruleParametersBlock ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==41) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalModelFragment.g:871:4: (lv_parameters_8_0= ruleParametersBlock )
                    {
                    // InternalModelFragment.g:871:4: (lv_parameters_8_0= ruleParametersBlock )
                    // InternalModelFragment.g:872:5: lv_parameters_8_0= ruleParametersBlock
                    {

                    					newCompositeNode(grammarAccess.getChunkTypeAccess().getParametersParametersBlockParserRuleCall_7_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_parameters_8_0=ruleParametersBlock();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getChunkTypeRule());
                    					}
                    					set(
                    						current,
                    						"parameters",
                    						lv_parameters_8_0,
                    						"org.jactr.io2.jactr.ModelFragment.ParametersBlock");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleChunkType"


    // $ANTLR start "entryRuleQualifiedNameWithWildcard"
    // InternalModelFragment.g:893:1: entryRuleQualifiedNameWithWildcard returns [String current=null] : iv_ruleQualifiedNameWithWildcard= ruleQualifiedNameWithWildcard EOF ;
    public final String entryRuleQualifiedNameWithWildcard() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedNameWithWildcard = null;


        try {
            // InternalModelFragment.g:893:65: (iv_ruleQualifiedNameWithWildcard= ruleQualifiedNameWithWildcard EOF )
            // InternalModelFragment.g:894:2: iv_ruleQualifiedNameWithWildcard= ruleQualifiedNameWithWildcard EOF
            {
             newCompositeNode(grammarAccess.getQualifiedNameWithWildcardRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedNameWithWildcard=ruleQualifiedNameWithWildcard();

            state._fsp--;

             current =iv_ruleQualifiedNameWithWildcard.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedNameWithWildcard"


    // $ANTLR start "ruleQualifiedNameWithWildcard"
    // InternalModelFragment.g:900:1: ruleQualifiedNameWithWildcard returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_QualifiedName_0= ruleQualifiedName kw= '.*' ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedNameWithWildcard() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_QualifiedName_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:906:2: ( (this_QualifiedName_0= ruleQualifiedName kw= '.*' ) )
            // InternalModelFragment.g:907:2: (this_QualifiedName_0= ruleQualifiedName kw= '.*' )
            {
            // InternalModelFragment.g:907:2: (this_QualifiedName_0= ruleQualifiedName kw= '.*' )
            // InternalModelFragment.g:908:3: this_QualifiedName_0= ruleQualifiedName kw= '.*'
            {

            			newCompositeNode(grammarAccess.getQualifiedNameWithWildcardAccess().getQualifiedNameParserRuleCall_0());
            		
            pushFollow(FOLLOW_20);
            this_QualifiedName_0=ruleQualifiedName();

            state._fsp--;


            			current.merge(this_QualifiedName_0);
            		

            			afterParserOrEnumRuleCall();
            		
            kw=(Token)match(input,25,FOLLOW_2); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getQualifiedNameWithWildcardAccess().getFullStopAsteriskKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedNameWithWildcard"


    // $ANTLR start "entryRuleQualifiedName"
    // InternalModelFragment.g:927:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final String entryRuleQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedName = null;


        try {
            // InternalModelFragment.g:927:53: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // InternalModelFragment.g:928:2: iv_ruleQualifiedName= ruleQualifiedName EOF
            {
             newCompositeNode(grammarAccess.getQualifiedNameRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedName=ruleQualifiedName();

            state._fsp--;

             current =iv_ruleQualifiedName.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // InternalModelFragment.g:934:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;


        	enterRule();

        try {
            // InternalModelFragment.g:940:2: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // InternalModelFragment.g:941:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // InternalModelFragment.g:941:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // InternalModelFragment.g:942:3: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_21); 

            			current.merge(this_ID_0);
            		

            			newLeafNode(this_ID_0, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0());
            		
            // InternalModelFragment.g:949:3: (kw= '.' this_ID_2= RULE_ID )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==26) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalModelFragment.g:950:4: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,26,FOLLOW_3); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0());
            	    			
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_21); 

            	    				current.merge(this_ID_2);
            	    			

            	    				newLeafNode(this_ID_2, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "ruleOverridable"
    // InternalModelFragment.g:968:1: ruleOverridable[EObject in_current] returns [EObject current=in_current] : ( (lv_override_0_0= ruleOVERRIDE ) )? ;
    public final EObject ruleOverridable(EObject in_current) throws RecognitionException {
        EObject current = in_current;

        AntlrDatatypeRuleToken lv_override_0_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:974:2: ( ( (lv_override_0_0= ruleOVERRIDE ) )? )
            // InternalModelFragment.g:975:2: ( (lv_override_0_0= ruleOVERRIDE ) )?
            {
            // InternalModelFragment.g:975:2: ( (lv_override_0_0= ruleOVERRIDE ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==46) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalModelFragment.g:976:3: (lv_override_0_0= ruleOVERRIDE )
                    {
                    // InternalModelFragment.g:976:3: (lv_override_0_0= ruleOVERRIDE )
                    // InternalModelFragment.g:977:4: lv_override_0_0= ruleOVERRIDE
                    {

                    				newCompositeNode(grammarAccess.getOverridableAccess().getOverrideOVERRIDEParserRuleCall_0());
                    			
                    pushFollow(FOLLOW_2);
                    lv_override_0_0=ruleOVERRIDE();

                    state._fsp--;


                    				if (current==null) {
                    					current = createModelElementForParent(grammarAccess.getOverridableRule());
                    				}
                    				set(
                    					current,
                    					"override",
                    					lv_override_0_0 != null,
                    					"org.jactr.io2.jactr.ModelFragment.OVERRIDE");
                    				afterParserOrEnumRuleCall();
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOverridable"


    // $ANTLR start "entryRuleBuffers"
    // InternalModelFragment.g:997:1: entryRuleBuffers returns [EObject current=null] : iv_ruleBuffers= ruleBuffers EOF ;
    public final EObject entryRuleBuffers() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBuffers = null;


        try {
            // InternalModelFragment.g:997:48: (iv_ruleBuffers= ruleBuffers EOF )
            // InternalModelFragment.g:998:2: iv_ruleBuffers= ruleBuffers EOF
            {
             newCompositeNode(grammarAccess.getBuffersRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBuffers=ruleBuffers();

            state._fsp--;

             current =iv_ruleBuffers; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBuffers"


    // $ANTLR start "ruleBuffers"
    // InternalModelFragment.g:1004:1: ruleBuffers returns [EObject current=null] : ( () otherlv_1= 'buffers' otherlv_2= '{' ( (lv_buffers_3_0= ruleBuffer ) )* otherlv_4= '}' ) ;
    public final EObject ruleBuffers() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_buffers_3_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:1010:2: ( ( () otherlv_1= 'buffers' otherlv_2= '{' ( (lv_buffers_3_0= ruleBuffer ) )* otherlv_4= '}' ) )
            // InternalModelFragment.g:1011:2: ( () otherlv_1= 'buffers' otherlv_2= '{' ( (lv_buffers_3_0= ruleBuffer ) )* otherlv_4= '}' )
            {
            // InternalModelFragment.g:1011:2: ( () otherlv_1= 'buffers' otherlv_2= '{' ( (lv_buffers_3_0= ruleBuffer ) )* otherlv_4= '}' )
            // InternalModelFragment.g:1012:3: () otherlv_1= 'buffers' otherlv_2= '{' ( (lv_buffers_3_0= ruleBuffer ) )* otherlv_4= '}'
            {
            // InternalModelFragment.g:1012:3: ()
            // InternalModelFragment.g:1013:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getBuffersAccess().getBuffersAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,27,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getBuffersAccess().getBuffersKeyword_1());
            		
            otherlv_2=(Token)match(input,14,FOLLOW_22); 

            			newLeafNode(otherlv_2, grammarAccess.getBuffersAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalModelFragment.g:1027:3: ( (lv_buffers_3_0= ruleBuffer ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_ID||LA20_0==23||LA20_0==28||LA20_0==46) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalModelFragment.g:1028:4: (lv_buffers_3_0= ruleBuffer )
            	    {
            	    // InternalModelFragment.g:1028:4: (lv_buffers_3_0= ruleBuffer )
            	    // InternalModelFragment.g:1029:5: lv_buffers_3_0= ruleBuffer
            	    {

            	    					newCompositeNode(grammarAccess.getBuffersAccess().getBuffersBufferParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_22);
            	    lv_buffers_3_0=ruleBuffer();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getBuffersRule());
            	    					}
            	    					add(
            	    						current,
            	    						"buffers",
            	    						lv_buffers_3_0,
            	    						"org.jactr.io2.jactr.ModelFragment.Buffer");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            otherlv_4=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getBuffersAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBuffers"


    // $ANTLR start "entryRuleBuffer"
    // InternalModelFragment.g:1054:1: entryRuleBuffer returns [EObject current=null] : iv_ruleBuffer= ruleBuffer EOF ;
    public final EObject entryRuleBuffer() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBuffer = null;


        try {
            // InternalModelFragment.g:1054:47: (iv_ruleBuffer= ruleBuffer EOF )
            // InternalModelFragment.g:1055:2: iv_ruleBuffer= ruleBuffer EOF
            {
             newCompositeNode(grammarAccess.getBufferRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBuffer=ruleBuffer();

            state._fsp--;

             current =iv_ruleBuffer; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBuffer"


    // $ANTLR start "ruleBuffer"
    // InternalModelFragment.g:1061:1: ruleBuffer returns [EObject current=null] : (this_Overridable_0= ruleOverridable[$current] ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) )* otherlv_4= '}' ( (lv_parameters_5_0= ruleParametersBlock ) )? ) ;
    public final EObject ruleBuffer() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_Overridable_0 = null;

        EObject lv_parameters_5_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:1067:2: ( (this_Overridable_0= ruleOverridable[$current] ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) )* otherlv_4= '}' ( (lv_parameters_5_0= ruleParametersBlock ) )? ) )
            // InternalModelFragment.g:1068:2: (this_Overridable_0= ruleOverridable[$current] ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) )* otherlv_4= '}' ( (lv_parameters_5_0= ruleParametersBlock ) )? )
            {
            // InternalModelFragment.g:1068:2: (this_Overridable_0= ruleOverridable[$current] ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) )* otherlv_4= '}' ( (lv_parameters_5_0= ruleParametersBlock ) )? )
            // InternalModelFragment.g:1069:3: this_Overridable_0= ruleOverridable[$current] ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) )* otherlv_4= '}' ( (lv_parameters_5_0= ruleParametersBlock ) )?
            {

            			if (current==null) {
            				current = createModelElement(grammarAccess.getBufferRule());
            			}
            			newCompositeNode(grammarAccess.getBufferAccess().getOverridableParserRuleCall_0());
            		
            pushFollow(FOLLOW_3);
            this_Overridable_0=ruleOverridable(current);

            state._fsp--;


            			current = this_Overridable_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalModelFragment.g:1080:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalModelFragment.g:1081:4: (lv_name_1_0= RULE_ID )
            {
            // InternalModelFragment.g:1081:4: (lv_name_1_0= RULE_ID )
            // InternalModelFragment.g:1082:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_name_1_0, grammarAccess.getBufferAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getBufferRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.jactr.io2.jactr.ModelFragment.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,14,FOLLOW_19); 

            			newLeafNode(otherlv_2, grammarAccess.getBufferAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalModelFragment.g:1102:3: ( ( ruleQualifiedName ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==RULE_ID) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalModelFragment.g:1103:4: ( ruleQualifiedName )
            	    {
            	    // InternalModelFragment.g:1103:4: ( ruleQualifiedName )
            	    // InternalModelFragment.g:1104:5: ruleQualifiedName
            	    {

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getBufferRule());
            	    					}
            	    				

            	    					newCompositeNode(grammarAccess.getBufferAccess().getSourceChunkDefCrossReference_3_0());
            	    				
            	    pushFollow(FOLLOW_19);
            	    ruleQualifiedName();

            	    state._fsp--;


            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            otherlv_4=(Token)match(input,15,FOLLOW_9); 

            			newLeafNode(otherlv_4, grammarAccess.getBufferAccess().getRightCurlyBracketKeyword_4());
            		
            // InternalModelFragment.g:1122:3: ( (lv_parameters_5_0= ruleParametersBlock ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==41) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalModelFragment.g:1123:4: (lv_parameters_5_0= ruleParametersBlock )
                    {
                    // InternalModelFragment.g:1123:4: (lv_parameters_5_0= ruleParametersBlock )
                    // InternalModelFragment.g:1124:5: lv_parameters_5_0= ruleParametersBlock
                    {

                    					newCompositeNode(grammarAccess.getBufferAccess().getParametersParametersBlockParserRuleCall_5_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_parameters_5_0=ruleParametersBlock();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getBufferRule());
                    					}
                    					set(
                    						current,
                    						"parameters",
                    						lv_parameters_5_0,
                    						"org.jactr.io2.jactr.ModelFragment.ParametersBlock");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBuffer"


    // $ANTLR start "entryRuleProduction"
    // InternalModelFragment.g:1145:1: entryRuleProduction returns [EObject current=null] : iv_ruleProduction= ruleProduction EOF ;
    public final EObject entryRuleProduction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProduction = null;


        try {
            // InternalModelFragment.g:1145:51: (iv_ruleProduction= ruleProduction EOF )
            // InternalModelFragment.g:1146:2: iv_ruleProduction= ruleProduction EOF
            {
             newCompositeNode(grammarAccess.getProductionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProduction=ruleProduction();

            state._fsp--;

             current =iv_ruleProduction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProduction"


    // $ANTLR start "ruleProduction"
    // InternalModelFragment.g:1152:1: ruleProduction returns [EObject current=null] : (this_Overridable_0= ruleOverridable[$current] otherlv_1= 'production' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' ( (lv_conditions_4_0= ruleProductionCondition ) )+ otherlv_5= '}' otherlv_6= '{' ( (lv_actions_7_0= ruleProductionAction ) )+ otherlv_8= '}' ( (lv_parameters_9_0= ruleParametersBlock ) )? ) ;
    public final EObject ruleProduction() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject this_Overridable_0 = null;

        EObject lv_conditions_4_0 = null;

        EObject lv_actions_7_0 = null;

        EObject lv_parameters_9_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:1158:2: ( (this_Overridable_0= ruleOverridable[$current] otherlv_1= 'production' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' ( (lv_conditions_4_0= ruleProductionCondition ) )+ otherlv_5= '}' otherlv_6= '{' ( (lv_actions_7_0= ruleProductionAction ) )+ otherlv_8= '}' ( (lv_parameters_9_0= ruleParametersBlock ) )? ) )
            // InternalModelFragment.g:1159:2: (this_Overridable_0= ruleOverridable[$current] otherlv_1= 'production' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' ( (lv_conditions_4_0= ruleProductionCondition ) )+ otherlv_5= '}' otherlv_6= '{' ( (lv_actions_7_0= ruleProductionAction ) )+ otherlv_8= '}' ( (lv_parameters_9_0= ruleParametersBlock ) )? )
            {
            // InternalModelFragment.g:1159:2: (this_Overridable_0= ruleOverridable[$current] otherlv_1= 'production' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' ( (lv_conditions_4_0= ruleProductionCondition ) )+ otherlv_5= '}' otherlv_6= '{' ( (lv_actions_7_0= ruleProductionAction ) )+ otherlv_8= '}' ( (lv_parameters_9_0= ruleParametersBlock ) )? )
            // InternalModelFragment.g:1160:3: this_Overridable_0= ruleOverridable[$current] otherlv_1= 'production' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' ( (lv_conditions_4_0= ruleProductionCondition ) )+ otherlv_5= '}' otherlv_6= '{' ( (lv_actions_7_0= ruleProductionAction ) )+ otherlv_8= '}' ( (lv_parameters_9_0= ruleParametersBlock ) )?
            {

            			if (current==null) {
            				current = createModelElement(grammarAccess.getProductionRule());
            			}
            			newCompositeNode(grammarAccess.getProductionAccess().getOverridableParserRuleCall_0());
            		
            pushFollow(FOLLOW_23);
            this_Overridable_0=ruleOverridable(current);

            state._fsp--;


            			current = this_Overridable_0;
            			afterParserOrEnumRuleCall();
            		
            otherlv_1=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getProductionAccess().getProductionKeyword_1());
            		
            // InternalModelFragment.g:1175:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalModelFragment.g:1176:4: (lv_name_2_0= RULE_ID )
            {
            // InternalModelFragment.g:1176:4: (lv_name_2_0= RULE_ID )
            // InternalModelFragment.g:1177:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_name_2_0, grammarAccess.getProductionAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getProductionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.jactr.io2.jactr.ModelFragment.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,14,FOLLOW_24); 

            			newLeafNode(otherlv_3, grammarAccess.getProductionAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalModelFragment.g:1197:3: ( (lv_conditions_4_0= ruleProductionCondition ) )+
            int cnt23=0;
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==RULE_ID||(LA23_0>=29 && LA23_0<=31)) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalModelFragment.g:1198:4: (lv_conditions_4_0= ruleProductionCondition )
            	    {
            	    // InternalModelFragment.g:1198:4: (lv_conditions_4_0= ruleProductionCondition )
            	    // InternalModelFragment.g:1199:5: lv_conditions_4_0= ruleProductionCondition
            	    {

            	    					newCompositeNode(grammarAccess.getProductionAccess().getConditionsProductionConditionParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_25);
            	    lv_conditions_4_0=ruleProductionCondition();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getProductionRule());
            	    					}
            	    					add(
            	    						current,
            	    						"conditions",
            	    						lv_conditions_4_0,
            	    						"org.jactr.io2.jactr.ModelFragment.ProductionCondition");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt23 >= 1 ) break loop23;
                        EarlyExitException eee =
                            new EarlyExitException(23, input);
                        throw eee;
                }
                cnt23++;
            } while (true);

            otherlv_5=(Token)match(input,15,FOLLOW_4); 

            			newLeafNode(otherlv_5, grammarAccess.getProductionAccess().getRightCurlyBracketKeyword_5());
            		
            otherlv_6=(Token)match(input,14,FOLLOW_26); 

            			newLeafNode(otherlv_6, grammarAccess.getProductionAccess().getLeftCurlyBracketKeyword_6());
            		
            // InternalModelFragment.g:1224:3: ( (lv_actions_7_0= ruleProductionAction ) )+
            int cnt24=0;
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==RULE_ID||(LA24_0>=30 && LA24_0<=34)) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalModelFragment.g:1225:4: (lv_actions_7_0= ruleProductionAction )
            	    {
            	    // InternalModelFragment.g:1225:4: (lv_actions_7_0= ruleProductionAction )
            	    // InternalModelFragment.g:1226:5: lv_actions_7_0= ruleProductionAction
            	    {

            	    					newCompositeNode(grammarAccess.getProductionAccess().getActionsProductionActionParserRuleCall_7_0());
            	    				
            	    pushFollow(FOLLOW_27);
            	    lv_actions_7_0=ruleProductionAction();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getProductionRule());
            	    					}
            	    					add(
            	    						current,
            	    						"actions",
            	    						lv_actions_7_0,
            	    						"org.jactr.io2.jactr.ModelFragment.ProductionAction");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt24 >= 1 ) break loop24;
                        EarlyExitException eee =
                            new EarlyExitException(24, input);
                        throw eee;
                }
                cnt24++;
            } while (true);

            otherlv_8=(Token)match(input,15,FOLLOW_9); 

            			newLeafNode(otherlv_8, grammarAccess.getProductionAccess().getRightCurlyBracketKeyword_8());
            		
            // InternalModelFragment.g:1247:3: ( (lv_parameters_9_0= ruleParametersBlock ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==41) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalModelFragment.g:1248:4: (lv_parameters_9_0= ruleParametersBlock )
                    {
                    // InternalModelFragment.g:1248:4: (lv_parameters_9_0= ruleParametersBlock )
                    // InternalModelFragment.g:1249:5: lv_parameters_9_0= ruleParametersBlock
                    {

                    					newCompositeNode(grammarAccess.getProductionAccess().getParametersParametersBlockParserRuleCall_9_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_parameters_9_0=ruleParametersBlock();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getProductionRule());
                    					}
                    					set(
                    						current,
                    						"parameters",
                    						lv_parameters_9_0,
                    						"org.jactr.io2.jactr.ModelFragment.ParametersBlock");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProduction"


    // $ANTLR start "entryRuleProductionCondition"
    // InternalModelFragment.g:1270:1: entryRuleProductionCondition returns [EObject current=null] : iv_ruleProductionCondition= ruleProductionCondition EOF ;
    public final EObject entryRuleProductionCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProductionCondition = null;


        try {
            // InternalModelFragment.g:1270:60: (iv_ruleProductionCondition= ruleProductionCondition EOF )
            // InternalModelFragment.g:1271:2: iv_ruleProductionCondition= ruleProductionCondition EOF
            {
             newCompositeNode(grammarAccess.getProductionConditionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProductionCondition=ruleProductionCondition();

            state._fsp--;

             current =iv_ruleProductionCondition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProductionCondition"


    // $ANTLR start "ruleProductionCondition"
    // InternalModelFragment.g:1277:1: ruleProductionCondition returns [EObject current=null] : (this_Match_0= ruleMatch | this_Query_1= ruleQuery | this_Script_2= ruleScript | this_Proxy_3= ruleProxy ) ;
    public final EObject ruleProductionCondition() throws RecognitionException {
        EObject current = null;

        EObject this_Match_0 = null;

        EObject this_Query_1 = null;

        EObject this_Script_2 = null;

        EObject this_Proxy_3 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:1283:2: ( (this_Match_0= ruleMatch | this_Query_1= ruleQuery | this_Script_2= ruleScript | this_Proxy_3= ruleProxy ) )
            // InternalModelFragment.g:1284:2: (this_Match_0= ruleMatch | this_Query_1= ruleQuery | this_Script_2= ruleScript | this_Proxy_3= ruleProxy )
            {
            // InternalModelFragment.g:1284:2: (this_Match_0= ruleMatch | this_Query_1= ruleQuery | this_Script_2= ruleScript | this_Proxy_3= ruleProxy )
            int alt26=4;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt26=1;
                }
                break;
            case 29:
                {
                alt26=2;
                }
                break;
            case 31:
                {
                alt26=3;
                }
                break;
            case 30:
                {
                alt26=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // InternalModelFragment.g:1285:3: this_Match_0= ruleMatch
                    {

                    			newCompositeNode(grammarAccess.getProductionConditionAccess().getMatchParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Match_0=ruleMatch();

                    state._fsp--;


                    			current = this_Match_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalModelFragment.g:1294:3: this_Query_1= ruleQuery
                    {

                    			newCompositeNode(grammarAccess.getProductionConditionAccess().getQueryParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Query_1=ruleQuery();

                    state._fsp--;


                    			current = this_Query_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalModelFragment.g:1303:3: this_Script_2= ruleScript
                    {

                    			newCompositeNode(grammarAccess.getProductionConditionAccess().getScriptParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_Script_2=ruleScript();

                    state._fsp--;


                    			current = this_Script_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalModelFragment.g:1312:3: this_Proxy_3= ruleProxy
                    {

                    			newCompositeNode(grammarAccess.getProductionConditionAccess().getProxyParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_Proxy_3=ruleProxy();

                    state._fsp--;


                    			current = this_Proxy_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProductionCondition"


    // $ANTLR start "entryRuleMatch"
    // InternalModelFragment.g:1324:1: entryRuleMatch returns [EObject current=null] : iv_ruleMatch= ruleMatch EOF ;
    public final EObject entryRuleMatch() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMatch = null;


        try {
            // InternalModelFragment.g:1324:46: (iv_ruleMatch= ruleMatch EOF )
            // InternalModelFragment.g:1325:2: iv_ruleMatch= ruleMatch EOF
            {
             newCompositeNode(grammarAccess.getMatchRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMatch=ruleMatch();

            state._fsp--;

             current =iv_ruleMatch; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMatch"


    // $ANTLR start "ruleMatch"
    // InternalModelFragment.g:1331:1: ruleMatch returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '{' ( (lv_isa_2_0= ruleIsaBlock ) ) ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' ) ;
    public final EObject ruleMatch() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_4=null;
        EObject lv_isa_2_0 = null;

        EObject lv_slots_3_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:1337:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '{' ( (lv_isa_2_0= ruleIsaBlock ) ) ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' ) )
            // InternalModelFragment.g:1338:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '{' ( (lv_isa_2_0= ruleIsaBlock ) ) ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' )
            {
            // InternalModelFragment.g:1338:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '{' ( (lv_isa_2_0= ruleIsaBlock ) ) ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' )
            // InternalModelFragment.g:1339:3: ( (otherlv_0= RULE_ID ) ) otherlv_1= '{' ( (lv_isa_2_0= ruleIsaBlock ) ) ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}'
            {
            // InternalModelFragment.g:1339:3: ( (otherlv_0= RULE_ID ) )
            // InternalModelFragment.g:1340:4: (otherlv_0= RULE_ID )
            {
            // InternalModelFragment.g:1340:4: (otherlv_0= RULE_ID )
            // InternalModelFragment.g:1341:5: otherlv_0= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMatchRule());
            					}
            				
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(otherlv_0, grammarAccess.getMatchAccess().getNameBufferCrossReference_0_0());
            				

            }


            }

            otherlv_1=(Token)match(input,14,FOLLOW_28); 

            			newLeafNode(otherlv_1, grammarAccess.getMatchAccess().getLeftCurlyBracketKeyword_1());
            		
            // InternalModelFragment.g:1356:3: ( (lv_isa_2_0= ruleIsaBlock ) )
            // InternalModelFragment.g:1357:4: (lv_isa_2_0= ruleIsaBlock )
            {
            // InternalModelFragment.g:1357:4: (lv_isa_2_0= ruleIsaBlock )
            // InternalModelFragment.g:1358:5: lv_isa_2_0= ruleIsaBlock
            {

            					newCompositeNode(grammarAccess.getMatchAccess().getIsaIsaBlockParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_29);
            lv_isa_2_0=ruleIsaBlock();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMatchRule());
            					}
            					set(
            						current,
            						"isa",
            						lv_isa_2_0,
            						"org.jactr.io2.jactr.ModelFragment.IsaBlock");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalModelFragment.g:1375:3: ( (lv_slots_3_0= ruleComplexSlot ) )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==RULE_ID||(LA27_0>=36 && LA27_0<=39)) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalModelFragment.g:1376:4: (lv_slots_3_0= ruleComplexSlot )
            	    {
            	    // InternalModelFragment.g:1376:4: (lv_slots_3_0= ruleComplexSlot )
            	    // InternalModelFragment.g:1377:5: lv_slots_3_0= ruleComplexSlot
            	    {

            	    					newCompositeNode(grammarAccess.getMatchAccess().getSlotsComplexSlotParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_29);
            	    lv_slots_3_0=ruleComplexSlot();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getMatchRule());
            	    					}
            	    					add(
            	    						current,
            	    						"slots",
            	    						lv_slots_3_0,
            	    						"org.jactr.io2.jactr.ModelFragment.ComplexSlot");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

            otherlv_4=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getMatchAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMatch"


    // $ANTLR start "entryRuleQuery"
    // InternalModelFragment.g:1402:1: entryRuleQuery returns [EObject current=null] : iv_ruleQuery= ruleQuery EOF ;
    public final EObject entryRuleQuery() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQuery = null;


        try {
            // InternalModelFragment.g:1402:46: (iv_ruleQuery= ruleQuery EOF )
            // InternalModelFragment.g:1403:2: iv_ruleQuery= ruleQuery EOF
            {
             newCompositeNode(grammarAccess.getQueryRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQuery=ruleQuery();

            state._fsp--;

             current =iv_ruleQuery; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQuery"


    // $ANTLR start "ruleQuery"
    // InternalModelFragment.g:1409:1: ruleQuery returns [EObject current=null] : (otherlv_0= '?' ( (otherlv_1= RULE_ID ) ) otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' ) ;
    public final EObject ruleQuery() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_slots_3_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:1415:2: ( (otherlv_0= '?' ( (otherlv_1= RULE_ID ) ) otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' ) )
            // InternalModelFragment.g:1416:2: (otherlv_0= '?' ( (otherlv_1= RULE_ID ) ) otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' )
            {
            // InternalModelFragment.g:1416:2: (otherlv_0= '?' ( (otherlv_1= RULE_ID ) ) otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' )
            // InternalModelFragment.g:1417:3: otherlv_0= '?' ( (otherlv_1= RULE_ID ) ) otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,29,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getQueryAccess().getQuestionMarkKeyword_0());
            		
            // InternalModelFragment.g:1421:3: ( (otherlv_1= RULE_ID ) )
            // InternalModelFragment.g:1422:4: (otherlv_1= RULE_ID )
            {
            // InternalModelFragment.g:1422:4: (otherlv_1= RULE_ID )
            // InternalModelFragment.g:1423:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getQueryRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(otherlv_1, grammarAccess.getQueryAccess().getNameBufferCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,14,FOLLOW_29); 

            			newLeafNode(otherlv_2, grammarAccess.getQueryAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalModelFragment.g:1438:3: ( (lv_slots_3_0= ruleComplexSlot ) )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==RULE_ID||(LA28_0>=36 && LA28_0<=39)) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // InternalModelFragment.g:1439:4: (lv_slots_3_0= ruleComplexSlot )
            	    {
            	    // InternalModelFragment.g:1439:4: (lv_slots_3_0= ruleComplexSlot )
            	    // InternalModelFragment.g:1440:5: lv_slots_3_0= ruleComplexSlot
            	    {

            	    					newCompositeNode(grammarAccess.getQueryAccess().getSlotsComplexSlotParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_29);
            	    lv_slots_3_0=ruleComplexSlot();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getQueryRule());
            	    					}
            	    					add(
            	    						current,
            	    						"slots",
            	    						lv_slots_3_0,
            	    						"org.jactr.io2.jactr.ModelFragment.ComplexSlot");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            otherlv_4=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getQueryAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQuery"


    // $ANTLR start "entryRuleProxy"
    // InternalModelFragment.g:1465:1: entryRuleProxy returns [EObject current=null] : iv_ruleProxy= ruleProxy EOF ;
    public final EObject entryRuleProxy() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProxy = null;


        try {
            // InternalModelFragment.g:1465:46: (iv_ruleProxy= ruleProxy EOF )
            // InternalModelFragment.g:1466:2: iv_ruleProxy= ruleProxy EOF
            {
             newCompositeNode(grammarAccess.getProxyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProxy=ruleProxy();

            state._fsp--;

             current =iv_ruleProxy; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProxy"


    // $ANTLR start "ruleProxy"
    // InternalModelFragment.g:1472:1: ruleProxy returns [EObject current=null] : (otherlv_0= 'proxy' otherlv_1= '(' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_slots_5_0= ruleSimpleSlot ) )* otherlv_6= '}' ) ;
    public final EObject ruleProxy() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_slots_5_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:1478:2: ( (otherlv_0= 'proxy' otherlv_1= '(' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_slots_5_0= ruleSimpleSlot ) )* otherlv_6= '}' ) )
            // InternalModelFragment.g:1479:2: (otherlv_0= 'proxy' otherlv_1= '(' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_slots_5_0= ruleSimpleSlot ) )* otherlv_6= '}' )
            {
            // InternalModelFragment.g:1479:2: (otherlv_0= 'proxy' otherlv_1= '(' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_slots_5_0= ruleSimpleSlot ) )* otherlv_6= '}' )
            // InternalModelFragment.g:1480:3: otherlv_0= 'proxy' otherlv_1= '(' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_slots_5_0= ruleSimpleSlot ) )* otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,30,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getProxyAccess().getProxyKeyword_0());
            		
            otherlv_1=(Token)match(input,20,FOLLOW_10); 

            			newLeafNode(otherlv_1, grammarAccess.getProxyAccess().getLeftParenthesisKeyword_1());
            		
            // InternalModelFragment.g:1488:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalModelFragment.g:1489:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalModelFragment.g:1489:4: (lv_name_2_0= RULE_STRING )
            // InternalModelFragment.g:1490:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_30); 

            					newLeafNode(lv_name_2_0, grammarAccess.getProxyAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getProxyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,22,FOLLOW_4); 

            			newLeafNode(otherlv_3, grammarAccess.getProxyAccess().getRightParenthesisKeyword_3());
            		
            otherlv_4=(Token)match(input,14,FOLLOW_19); 

            			newLeafNode(otherlv_4, grammarAccess.getProxyAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalModelFragment.g:1514:3: ( (lv_slots_5_0= ruleSimpleSlot ) )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==RULE_ID) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalModelFragment.g:1515:4: (lv_slots_5_0= ruleSimpleSlot )
            	    {
            	    // InternalModelFragment.g:1515:4: (lv_slots_5_0= ruleSimpleSlot )
            	    // InternalModelFragment.g:1516:5: lv_slots_5_0= ruleSimpleSlot
            	    {

            	    					newCompositeNode(grammarAccess.getProxyAccess().getSlotsSimpleSlotParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_19);
            	    lv_slots_5_0=ruleSimpleSlot();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getProxyRule());
            	    					}
            	    					add(
            	    						current,
            	    						"slots",
            	    						lv_slots_5_0,
            	    						"org.jactr.io2.jactr.ModelFragment.SimpleSlot");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);

            otherlv_6=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getProxyAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProxy"


    // $ANTLR start "entryRuleScript"
    // InternalModelFragment.g:1541:1: entryRuleScript returns [EObject current=null] : iv_ruleScript= ruleScript EOF ;
    public final EObject entryRuleScript() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScript = null;


        try {
            // InternalModelFragment.g:1541:47: (iv_ruleScript= ruleScript EOF )
            // InternalModelFragment.g:1542:2: iv_ruleScript= ruleScript EOF
            {
             newCompositeNode(grammarAccess.getScriptRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleScript=ruleScript();

            state._fsp--;

             current =iv_ruleScript; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleScript"


    // $ANTLR start "ruleScript"
    // InternalModelFragment.g:1548:1: ruleScript returns [EObject current=null] : (otherlv_0= 'script' otherlv_1= '(' ( (lv_language_2_0= RULE_STRING ) ) otherlv_3= ')' ( (lv_script_4_0= RULE_CODE ) ) ) ;
    public final EObject ruleScript() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_language_2_0=null;
        Token otherlv_3=null;
        Token lv_script_4_0=null;


        	enterRule();

        try {
            // InternalModelFragment.g:1554:2: ( (otherlv_0= 'script' otherlv_1= '(' ( (lv_language_2_0= RULE_STRING ) ) otherlv_3= ')' ( (lv_script_4_0= RULE_CODE ) ) ) )
            // InternalModelFragment.g:1555:2: (otherlv_0= 'script' otherlv_1= '(' ( (lv_language_2_0= RULE_STRING ) ) otherlv_3= ')' ( (lv_script_4_0= RULE_CODE ) ) )
            {
            // InternalModelFragment.g:1555:2: (otherlv_0= 'script' otherlv_1= '(' ( (lv_language_2_0= RULE_STRING ) ) otherlv_3= ')' ( (lv_script_4_0= RULE_CODE ) ) )
            // InternalModelFragment.g:1556:3: otherlv_0= 'script' otherlv_1= '(' ( (lv_language_2_0= RULE_STRING ) ) otherlv_3= ')' ( (lv_script_4_0= RULE_CODE ) )
            {
            otherlv_0=(Token)match(input,31,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getScriptAccess().getScriptKeyword_0());
            		
            otherlv_1=(Token)match(input,20,FOLLOW_10); 

            			newLeafNode(otherlv_1, grammarAccess.getScriptAccess().getLeftParenthesisKeyword_1());
            		
            // InternalModelFragment.g:1564:3: ( (lv_language_2_0= RULE_STRING ) )
            // InternalModelFragment.g:1565:4: (lv_language_2_0= RULE_STRING )
            {
            // InternalModelFragment.g:1565:4: (lv_language_2_0= RULE_STRING )
            // InternalModelFragment.g:1566:5: lv_language_2_0= RULE_STRING
            {
            lv_language_2_0=(Token)match(input,RULE_STRING,FOLLOW_30); 

            					newLeafNode(lv_language_2_0, grammarAccess.getScriptAccess().getLanguageSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getScriptRule());
            					}
            					setWithLastConsumed(
            						current,
            						"language",
            						lv_language_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,22,FOLLOW_31); 

            			newLeafNode(otherlv_3, grammarAccess.getScriptAccess().getRightParenthesisKeyword_3());
            		
            // InternalModelFragment.g:1586:3: ( (lv_script_4_0= RULE_CODE ) )
            // InternalModelFragment.g:1587:4: (lv_script_4_0= RULE_CODE )
            {
            // InternalModelFragment.g:1587:4: (lv_script_4_0= RULE_CODE )
            // InternalModelFragment.g:1588:5: lv_script_4_0= RULE_CODE
            {
            lv_script_4_0=(Token)match(input,RULE_CODE,FOLLOW_2); 

            					newLeafNode(lv_script_4_0, grammarAccess.getScriptAccess().getScriptCODETerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getScriptRule());
            					}
            					setWithLastConsumed(
            						current,
            						"script",
            						lv_script_4_0,
            						"org.jactr.io2.jactr.ModelFragment.CODE");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleScript"


    // $ANTLR start "entryRuleProductionAction"
    // InternalModelFragment.g:1608:1: entryRuleProductionAction returns [EObject current=null] : iv_ruleProductionAction= ruleProductionAction EOF ;
    public final EObject entryRuleProductionAction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProductionAction = null;


        try {
            // InternalModelFragment.g:1608:57: (iv_ruleProductionAction= ruleProductionAction EOF )
            // InternalModelFragment.g:1609:2: iv_ruleProductionAction= ruleProductionAction EOF
            {
             newCompositeNode(grammarAccess.getProductionActionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProductionAction=ruleProductionAction();

            state._fsp--;

             current =iv_ruleProductionAction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProductionAction"


    // $ANTLR start "ruleProductionAction"
    // InternalModelFragment.g:1615:1: ruleProductionAction returns [EObject current=null] : (this_Add_0= ruleAdd | this_Remove_1= ruleRemove | this_Modify_2= ruleModify | this_Script_3= ruleScript | this_Proxy_4= ruleProxy | this_Output_5= ruleOutput ) ;
    public final EObject ruleProductionAction() throws RecognitionException {
        EObject current = null;

        EObject this_Add_0 = null;

        EObject this_Remove_1 = null;

        EObject this_Modify_2 = null;

        EObject this_Script_3 = null;

        EObject this_Proxy_4 = null;

        EObject this_Output_5 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:1621:2: ( (this_Add_0= ruleAdd | this_Remove_1= ruleRemove | this_Modify_2= ruleModify | this_Script_3= ruleScript | this_Proxy_4= ruleProxy | this_Output_5= ruleOutput ) )
            // InternalModelFragment.g:1622:2: (this_Add_0= ruleAdd | this_Remove_1= ruleRemove | this_Modify_2= ruleModify | this_Script_3= ruleScript | this_Proxy_4= ruleProxy | this_Output_5= ruleOutput )
            {
            // InternalModelFragment.g:1622:2: (this_Add_0= ruleAdd | this_Remove_1= ruleRemove | this_Modify_2= ruleModify | this_Script_3= ruleScript | this_Proxy_4= ruleProxy | this_Output_5= ruleOutput )
            int alt30=6;
            switch ( input.LA(1) ) {
            case 32:
                {
                alt30=1;
                }
                break;
            case 33:
                {
                alt30=2;
                }
                break;
            case RULE_ID:
                {
                alt30=3;
                }
                break;
            case 31:
                {
                alt30=4;
                }
                break;
            case 30:
                {
                alt30=5;
                }
                break;
            case 34:
                {
                alt30=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // InternalModelFragment.g:1623:3: this_Add_0= ruleAdd
                    {

                    			newCompositeNode(grammarAccess.getProductionActionAccess().getAddParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Add_0=ruleAdd();

                    state._fsp--;


                    			current = this_Add_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalModelFragment.g:1632:3: this_Remove_1= ruleRemove
                    {

                    			newCompositeNode(grammarAccess.getProductionActionAccess().getRemoveParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Remove_1=ruleRemove();

                    state._fsp--;


                    			current = this_Remove_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalModelFragment.g:1641:3: this_Modify_2= ruleModify
                    {

                    			newCompositeNode(grammarAccess.getProductionActionAccess().getModifyParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_Modify_2=ruleModify();

                    state._fsp--;


                    			current = this_Modify_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalModelFragment.g:1650:3: this_Script_3= ruleScript
                    {

                    			newCompositeNode(grammarAccess.getProductionActionAccess().getScriptParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_Script_3=ruleScript();

                    state._fsp--;


                    			current = this_Script_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalModelFragment.g:1659:3: this_Proxy_4= ruleProxy
                    {

                    			newCompositeNode(grammarAccess.getProductionActionAccess().getProxyParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_Proxy_4=ruleProxy();

                    state._fsp--;


                    			current = this_Proxy_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalModelFragment.g:1668:3: this_Output_5= ruleOutput
                    {

                    			newCompositeNode(grammarAccess.getProductionActionAccess().getOutputParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_Output_5=ruleOutput();

                    state._fsp--;


                    			current = this_Output_5;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProductionAction"


    // $ANTLR start "entryRuleAdd"
    // InternalModelFragment.g:1680:1: entryRuleAdd returns [EObject current=null] : iv_ruleAdd= ruleAdd EOF ;
    public final EObject entryRuleAdd() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdd = null;


        try {
            // InternalModelFragment.g:1680:44: (iv_ruleAdd= ruleAdd EOF )
            // InternalModelFragment.g:1681:2: iv_ruleAdd= ruleAdd EOF
            {
             newCompositeNode(grammarAccess.getAddRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAdd=ruleAdd();

            state._fsp--;

             current =iv_ruleAdd; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAdd"


    // $ANTLR start "ruleAdd"
    // InternalModelFragment.g:1687:1: ruleAdd returns [EObject current=null] : (otherlv_0= '+' ( (otherlv_1= RULE_ID ) ) otherlv_2= '{' ( (lv_isa_3_0= ruleIsaBlock ) )? ( (lv_slots_4_0= ruleConditionalSlot ) )* otherlv_5= '}' ) ;
    public final EObject ruleAdd() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        EObject lv_isa_3_0 = null;

        EObject lv_slots_4_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:1693:2: ( (otherlv_0= '+' ( (otherlv_1= RULE_ID ) ) otherlv_2= '{' ( (lv_isa_3_0= ruleIsaBlock ) )? ( (lv_slots_4_0= ruleConditionalSlot ) )* otherlv_5= '}' ) )
            // InternalModelFragment.g:1694:2: (otherlv_0= '+' ( (otherlv_1= RULE_ID ) ) otherlv_2= '{' ( (lv_isa_3_0= ruleIsaBlock ) )? ( (lv_slots_4_0= ruleConditionalSlot ) )* otherlv_5= '}' )
            {
            // InternalModelFragment.g:1694:2: (otherlv_0= '+' ( (otherlv_1= RULE_ID ) ) otherlv_2= '{' ( (lv_isa_3_0= ruleIsaBlock ) )? ( (lv_slots_4_0= ruleConditionalSlot ) )* otherlv_5= '}' )
            // InternalModelFragment.g:1695:3: otherlv_0= '+' ( (otherlv_1= RULE_ID ) ) otherlv_2= '{' ( (lv_isa_3_0= ruleIsaBlock ) )? ( (lv_slots_4_0= ruleConditionalSlot ) )* otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,32,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getAddAccess().getPlusSignKeyword_0());
            		
            // InternalModelFragment.g:1699:3: ( (otherlv_1= RULE_ID ) )
            // InternalModelFragment.g:1700:4: (otherlv_1= RULE_ID )
            {
            // InternalModelFragment.g:1700:4: (otherlv_1= RULE_ID )
            // InternalModelFragment.g:1701:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAddRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(otherlv_1, grammarAccess.getAddAccess().getNameBufferCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,14,FOLLOW_29); 

            			newLeafNode(otherlv_2, grammarAccess.getAddAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalModelFragment.g:1716:3: ( (lv_isa_3_0= ruleIsaBlock ) )?
            int alt31=2;
            alt31 = dfa31.predict(input);
            switch (alt31) {
                case 1 :
                    // InternalModelFragment.g:1717:4: (lv_isa_3_0= ruleIsaBlock )
                    {
                    // InternalModelFragment.g:1717:4: (lv_isa_3_0= ruleIsaBlock )
                    // InternalModelFragment.g:1718:5: lv_isa_3_0= ruleIsaBlock
                    {

                    					newCompositeNode(grammarAccess.getAddAccess().getIsaIsaBlockParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_29);
                    lv_isa_3_0=ruleIsaBlock();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getAddRule());
                    					}
                    					set(
                    						current,
                    						"isa",
                    						lv_isa_3_0,
                    						"org.jactr.io2.jactr.ModelFragment.IsaBlock");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalModelFragment.g:1735:3: ( (lv_slots_4_0= ruleConditionalSlot ) )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==RULE_ID||LA32_0==36) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalModelFragment.g:1736:4: (lv_slots_4_0= ruleConditionalSlot )
            	    {
            	    // InternalModelFragment.g:1736:4: (lv_slots_4_0= ruleConditionalSlot )
            	    // InternalModelFragment.g:1737:5: lv_slots_4_0= ruleConditionalSlot
            	    {

            	    					newCompositeNode(grammarAccess.getAddAccess().getSlotsConditionalSlotParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_29);
            	    lv_slots_4_0=ruleConditionalSlot();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getAddRule());
            	    					}
            	    					add(
            	    						current,
            	    						"slots",
            	    						lv_slots_4_0,
            	    						"org.jactr.io2.jactr.ModelFragment.ConditionalSlot");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

            otherlv_5=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getAddAccess().getRightCurlyBracketKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAdd"


    // $ANTLR start "entryRuleRemove"
    // InternalModelFragment.g:1762:1: entryRuleRemove returns [EObject current=null] : iv_ruleRemove= ruleRemove EOF ;
    public final EObject entryRuleRemove() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRemove = null;


        try {
            // InternalModelFragment.g:1762:47: (iv_ruleRemove= ruleRemove EOF )
            // InternalModelFragment.g:1763:2: iv_ruleRemove= ruleRemove EOF
            {
             newCompositeNode(grammarAccess.getRemoveRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRemove=ruleRemove();

            state._fsp--;

             current =iv_ruleRemove; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRemove"


    // $ANTLR start "ruleRemove"
    // InternalModelFragment.g:1769:1: ruleRemove returns [EObject current=null] : (otherlv_0= '-' this_SimpleSlotBlock_1= ruleSimpleSlotBlock[$current] ) ;
    public final EObject ruleRemove() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject this_SimpleSlotBlock_1 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:1775:2: ( (otherlv_0= '-' this_SimpleSlotBlock_1= ruleSimpleSlotBlock[$current] ) )
            // InternalModelFragment.g:1776:2: (otherlv_0= '-' this_SimpleSlotBlock_1= ruleSimpleSlotBlock[$current] )
            {
            // InternalModelFragment.g:1776:2: (otherlv_0= '-' this_SimpleSlotBlock_1= ruleSimpleSlotBlock[$current] )
            // InternalModelFragment.g:1777:3: otherlv_0= '-' this_SimpleSlotBlock_1= ruleSimpleSlotBlock[$current]
            {
            otherlv_0=(Token)match(input,33,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getRemoveAccess().getHyphenMinusKeyword_0());
            		

            			if (current==null) {
            				current = createModelElement(grammarAccess.getRemoveRule());
            			}
            			newCompositeNode(grammarAccess.getRemoveAccess().getSimpleSlotBlockParserRuleCall_1());
            		
            pushFollow(FOLLOW_2);
            this_SimpleSlotBlock_1=ruleSimpleSlotBlock(current);

            state._fsp--;


            			current = this_SimpleSlotBlock_1;
            			afterParserOrEnumRuleCall();
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRemove"


    // $ANTLR start "entryRuleModify"
    // InternalModelFragment.g:1796:1: entryRuleModify returns [EObject current=null] : iv_ruleModify= ruleModify EOF ;
    public final EObject entryRuleModify() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModify = null;


        try {
            // InternalModelFragment.g:1796:47: (iv_ruleModify= ruleModify EOF )
            // InternalModelFragment.g:1797:2: iv_ruleModify= ruleModify EOF
            {
             newCompositeNode(grammarAccess.getModifyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModify=ruleModify();

            state._fsp--;

             current =iv_ruleModify; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModify"


    // $ANTLR start "ruleModify"
    // InternalModelFragment.g:1803:1: ruleModify returns [EObject current=null] : this_SimpleSlotBlock_0= ruleSimpleSlotBlock[$current] ;
    public final EObject ruleModify() throws RecognitionException {
        EObject current = null;

        EObject this_SimpleSlotBlock_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:1809:2: (this_SimpleSlotBlock_0= ruleSimpleSlotBlock[$current] )
            // InternalModelFragment.g:1810:2: this_SimpleSlotBlock_0= ruleSimpleSlotBlock[$current]
            {

            		if (current==null) {
            			current = createModelElement(grammarAccess.getModifyRule());
            		}
            		newCompositeNode(grammarAccess.getModifyAccess().getSimpleSlotBlockParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_SimpleSlotBlock_0=ruleSimpleSlotBlock(current);

            state._fsp--;


            		current = this_SimpleSlotBlock_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModify"


    // $ANTLR start "entryRuleOutput"
    // InternalModelFragment.g:1824:1: entryRuleOutput returns [EObject current=null] : iv_ruleOutput= ruleOutput EOF ;
    public final EObject entryRuleOutput() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOutput = null;


        try {
            // InternalModelFragment.g:1824:47: (iv_ruleOutput= ruleOutput EOF )
            // InternalModelFragment.g:1825:2: iv_ruleOutput= ruleOutput EOF
            {
             newCompositeNode(grammarAccess.getOutputRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOutput=ruleOutput();

            state._fsp--;

             current =iv_ruleOutput; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOutput"


    // $ANTLR start "ruleOutput"
    // InternalModelFragment.g:1831:1: ruleOutput returns [EObject current=null] : (otherlv_0= 'output' otherlv_1= '(' ( (lv_string_2_0= RULE_STRING ) ) otherlv_3= ')' ) ;
    public final EObject ruleOutput() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_string_2_0=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalModelFragment.g:1837:2: ( (otherlv_0= 'output' otherlv_1= '(' ( (lv_string_2_0= RULE_STRING ) ) otherlv_3= ')' ) )
            // InternalModelFragment.g:1838:2: (otherlv_0= 'output' otherlv_1= '(' ( (lv_string_2_0= RULE_STRING ) ) otherlv_3= ')' )
            {
            // InternalModelFragment.g:1838:2: (otherlv_0= 'output' otherlv_1= '(' ( (lv_string_2_0= RULE_STRING ) ) otherlv_3= ')' )
            // InternalModelFragment.g:1839:3: otherlv_0= 'output' otherlv_1= '(' ( (lv_string_2_0= RULE_STRING ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,34,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getOutputAccess().getOutputKeyword_0());
            		
            otherlv_1=(Token)match(input,20,FOLLOW_10); 

            			newLeafNode(otherlv_1, grammarAccess.getOutputAccess().getLeftParenthesisKeyword_1());
            		
            // InternalModelFragment.g:1847:3: ( (lv_string_2_0= RULE_STRING ) )
            // InternalModelFragment.g:1848:4: (lv_string_2_0= RULE_STRING )
            {
            // InternalModelFragment.g:1848:4: (lv_string_2_0= RULE_STRING )
            // InternalModelFragment.g:1849:5: lv_string_2_0= RULE_STRING
            {
            lv_string_2_0=(Token)match(input,RULE_STRING,FOLLOW_30); 

            					newLeafNode(lv_string_2_0, grammarAccess.getOutputAccess().getStringSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getOutputRule());
            					}
            					setWithLastConsumed(
            						current,
            						"string",
            						lv_string_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getOutputAccess().getRightParenthesisKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOutput"


    // $ANTLR start "ruleSimpleSlotBlock"
    // InternalModelFragment.g:1874:1: ruleSimpleSlotBlock[EObject in_current] returns [EObject current=in_current] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '{' ( (lv_slots_2_0= ruleSimpleSlot ) )* otherlv_3= '}' ) ;
    public final EObject ruleSimpleSlotBlock(EObject in_current) throws RecognitionException {
        EObject current = in_current;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_slots_2_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:1880:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '{' ( (lv_slots_2_0= ruleSimpleSlot ) )* otherlv_3= '}' ) )
            // InternalModelFragment.g:1881:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '{' ( (lv_slots_2_0= ruleSimpleSlot ) )* otherlv_3= '}' )
            {
            // InternalModelFragment.g:1881:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '{' ( (lv_slots_2_0= ruleSimpleSlot ) )* otherlv_3= '}' )
            // InternalModelFragment.g:1882:3: ( (otherlv_0= RULE_ID ) ) otherlv_1= '{' ( (lv_slots_2_0= ruleSimpleSlot ) )* otherlv_3= '}'
            {
            // InternalModelFragment.g:1882:3: ( (otherlv_0= RULE_ID ) )
            // InternalModelFragment.g:1883:4: (otherlv_0= RULE_ID )
            {
            // InternalModelFragment.g:1883:4: (otherlv_0= RULE_ID )
            // InternalModelFragment.g:1884:5: otherlv_0= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSimpleSlotBlockRule());
            					}
            				
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(otherlv_0, grammarAccess.getSimpleSlotBlockAccess().getNameBufferCrossReference_0_0());
            				

            }


            }

            otherlv_1=(Token)match(input,14,FOLLOW_19); 

            			newLeafNode(otherlv_1, grammarAccess.getSimpleSlotBlockAccess().getLeftCurlyBracketKeyword_1());
            		
            // InternalModelFragment.g:1899:3: ( (lv_slots_2_0= ruleSimpleSlot ) )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==RULE_ID) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalModelFragment.g:1900:4: (lv_slots_2_0= ruleSimpleSlot )
            	    {
            	    // InternalModelFragment.g:1900:4: (lv_slots_2_0= ruleSimpleSlot )
            	    // InternalModelFragment.g:1901:5: lv_slots_2_0= ruleSimpleSlot
            	    {

            	    					newCompositeNode(grammarAccess.getSimpleSlotBlockAccess().getSlotsSimpleSlotParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_19);
            	    lv_slots_2_0=ruleSimpleSlot();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getSimpleSlotBlockRule());
            	    					}
            	    					add(
            	    						current,
            	    						"slots",
            	    						lv_slots_2_0,
            	    						"org.jactr.io2.jactr.ModelFragment.SimpleSlot");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);

            otherlv_3=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getSimpleSlotBlockAccess().getRightCurlyBracketKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleSlotBlock"


    // $ANTLR start "entryRuleIsaBlock"
    // InternalModelFragment.g:1926:1: entryRuleIsaBlock returns [EObject current=null] : iv_ruleIsaBlock= ruleIsaBlock EOF ;
    public final EObject entryRuleIsaBlock() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIsaBlock = null;


        try {
            // InternalModelFragment.g:1926:49: (iv_ruleIsaBlock= ruleIsaBlock EOF )
            // InternalModelFragment.g:1927:2: iv_ruleIsaBlock= ruleIsaBlock EOF
            {
             newCompositeNode(grammarAccess.getIsaBlockRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIsaBlock=ruleIsaBlock();

            state._fsp--;

             current =iv_ruleIsaBlock; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIsaBlock"


    // $ANTLR start "ruleIsaBlock"
    // InternalModelFragment.g:1933:1: ruleIsaBlock returns [EObject current=null] : ( (otherlv_0= 'isa' ( ( ruleQualifiedName ) ) ) | ( ( ruleQualifiedName ) ) | ( (lv_name_3_0= ruleVARIABLE ) ) ) ;
    public final EObject ruleIsaBlock() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:1939:2: ( ( (otherlv_0= 'isa' ( ( ruleQualifiedName ) ) ) | ( ( ruleQualifiedName ) ) | ( (lv_name_3_0= ruleVARIABLE ) ) ) )
            // InternalModelFragment.g:1940:2: ( (otherlv_0= 'isa' ( ( ruleQualifiedName ) ) ) | ( ( ruleQualifiedName ) ) | ( (lv_name_3_0= ruleVARIABLE ) ) )
            {
            // InternalModelFragment.g:1940:2: ( (otherlv_0= 'isa' ( ( ruleQualifiedName ) ) ) | ( ( ruleQualifiedName ) ) | ( (lv_name_3_0= ruleVARIABLE ) ) )
            int alt34=3;
            switch ( input.LA(1) ) {
            case 35:
                {
                alt34=1;
                }
                break;
            case RULE_ID:
                {
                alt34=2;
                }
                break;
            case 36:
                {
                alt34=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // InternalModelFragment.g:1941:3: (otherlv_0= 'isa' ( ( ruleQualifiedName ) ) )
                    {
                    // InternalModelFragment.g:1941:3: (otherlv_0= 'isa' ( ( ruleQualifiedName ) ) )
                    // InternalModelFragment.g:1942:4: otherlv_0= 'isa' ( ( ruleQualifiedName ) )
                    {
                    otherlv_0=(Token)match(input,35,FOLLOW_3); 

                    				newLeafNode(otherlv_0, grammarAccess.getIsaBlockAccess().getIsaKeyword_0_0());
                    			
                    // InternalModelFragment.g:1946:4: ( ( ruleQualifiedName ) )
                    // InternalModelFragment.g:1947:5: ( ruleQualifiedName )
                    {
                    // InternalModelFragment.g:1947:5: ( ruleQualifiedName )
                    // InternalModelFragment.g:1948:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getIsaBlockRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getIsaBlockAccess().getTypeChunkTypeCrossReference_0_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalModelFragment.g:1964:3: ( ( ruleQualifiedName ) )
                    {
                    // InternalModelFragment.g:1964:3: ( ( ruleQualifiedName ) )
                    // InternalModelFragment.g:1965:4: ( ruleQualifiedName )
                    {
                    // InternalModelFragment.g:1965:4: ( ruleQualifiedName )
                    // InternalModelFragment.g:1966:5: ruleQualifiedName
                    {

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getIsaBlockRule());
                    					}
                    				

                    					newCompositeNode(grammarAccess.getIsaBlockAccess().getChunkChunkDefCrossReference_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    ruleQualifiedName();

                    state._fsp--;


                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalModelFragment.g:1981:3: ( (lv_name_3_0= ruleVARIABLE ) )
                    {
                    // InternalModelFragment.g:1981:3: ( (lv_name_3_0= ruleVARIABLE ) )
                    // InternalModelFragment.g:1982:4: (lv_name_3_0= ruleVARIABLE )
                    {
                    // InternalModelFragment.g:1982:4: (lv_name_3_0= ruleVARIABLE )
                    // InternalModelFragment.g:1983:5: lv_name_3_0= ruleVARIABLE
                    {

                    					newCompositeNode(grammarAccess.getIsaBlockAccess().getNameVARIABLEParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_name_3_0=ruleVARIABLE();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getIsaBlockRule());
                    					}
                    					set(
                    						current,
                    						"name",
                    						lv_name_3_0,
                    						"org.jactr.io2.jactr.ModelFragment.VARIABLE");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIsaBlock"


    // $ANTLR start "entryRuleSimpleSlot"
    // InternalModelFragment.g:2004:1: entryRuleSimpleSlot returns [EObject current=null] : iv_ruleSimpleSlot= ruleSimpleSlot EOF ;
    public final EObject entryRuleSimpleSlot() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleSlot = null;


        try {
            // InternalModelFragment.g:2004:51: (iv_ruleSimpleSlot= ruleSimpleSlot EOF )
            // InternalModelFragment.g:2005:2: iv_ruleSimpleSlot= ruleSimpleSlot EOF
            {
             newCompositeNode(grammarAccess.getSimpleSlotRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSimpleSlot=ruleSimpleSlot();

            state._fsp--;

             current =iv_ruleSimpleSlot; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSimpleSlot"


    // $ANTLR start "ruleSimpleSlot"
    // InternalModelFragment.g:2011:1: ruleSimpleSlot returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_value_2_0= ruleValue ) ) ) ;
    public final EObject ruleSimpleSlot() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        EObject lv_value_2_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:2017:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_value_2_0= ruleValue ) ) ) )
            // InternalModelFragment.g:2018:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_value_2_0= ruleValue ) ) )
            {
            // InternalModelFragment.g:2018:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_value_2_0= ruleValue ) ) )
            // InternalModelFragment.g:2019:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_value_2_0= ruleValue ) )
            {
            // InternalModelFragment.g:2019:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalModelFragment.g:2020:4: (lv_name_0_0= RULE_ID )
            {
            // InternalModelFragment.g:2020:4: (lv_name_0_0= RULE_ID )
            // InternalModelFragment.g:2021:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_32); 

            					newLeafNode(lv_name_0_0, grammarAccess.getSimpleSlotAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSimpleSlotRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"org.jactr.io2.jactr.ModelFragment.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,36,FOLLOW_33); 

            			newLeafNode(otherlv_1, grammarAccess.getSimpleSlotAccess().getEqualsSignKeyword_1());
            		
            // InternalModelFragment.g:2041:3: ( (lv_value_2_0= ruleValue ) )
            // InternalModelFragment.g:2042:4: (lv_value_2_0= ruleValue )
            {
            // InternalModelFragment.g:2042:4: (lv_value_2_0= ruleValue )
            // InternalModelFragment.g:2043:5: lv_value_2_0= ruleValue
            {

            					newCompositeNode(grammarAccess.getSimpleSlotAccess().getValueValueParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_value_2_0=ruleValue();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSimpleSlotRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_2_0,
            						"org.jactr.io2.jactr.ModelFragment.Value");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleSlot"


    // $ANTLR start "entryRuleComplexSlot"
    // InternalModelFragment.g:2064:1: entryRuleComplexSlot returns [EObject current=null] : iv_ruleComplexSlot= ruleComplexSlot EOF ;
    public final EObject entryRuleComplexSlot() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComplexSlot = null;


        try {
            // InternalModelFragment.g:2064:52: (iv_ruleComplexSlot= ruleComplexSlot EOF )
            // InternalModelFragment.g:2065:2: iv_ruleComplexSlot= ruleComplexSlot EOF
            {
             newCompositeNode(grammarAccess.getComplexSlotRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComplexSlot=ruleComplexSlot();

            state._fsp--;

             current =iv_ruleComplexSlot; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComplexSlot"


    // $ANTLR start "ruleComplexSlot"
    // InternalModelFragment.g:2071:1: ruleComplexSlot returns [EObject current=null] : (this_BooleanOperation_0= ruleBooleanOperation | this_ConditionalSlot_1= ruleConditionalSlot ) ;
    public final EObject ruleComplexSlot() throws RecognitionException {
        EObject current = null;

        EObject this_BooleanOperation_0 = null;

        EObject this_ConditionalSlot_1 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:2077:2: ( (this_BooleanOperation_0= ruleBooleanOperation | this_ConditionalSlot_1= ruleConditionalSlot ) )
            // InternalModelFragment.g:2078:2: (this_BooleanOperation_0= ruleBooleanOperation | this_ConditionalSlot_1= ruleConditionalSlot )
            {
            // InternalModelFragment.g:2078:2: (this_BooleanOperation_0= ruleBooleanOperation | this_ConditionalSlot_1= ruleConditionalSlot )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( ((LA35_0>=37 && LA35_0<=39)) ) {
                alt35=1;
            }
            else if ( (LA35_0==RULE_ID||LA35_0==36) ) {
                alt35=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // InternalModelFragment.g:2079:3: this_BooleanOperation_0= ruleBooleanOperation
                    {

                    			newCompositeNode(grammarAccess.getComplexSlotAccess().getBooleanOperationParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_BooleanOperation_0=ruleBooleanOperation();

                    state._fsp--;


                    			current = this_BooleanOperation_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalModelFragment.g:2088:3: this_ConditionalSlot_1= ruleConditionalSlot
                    {

                    			newCompositeNode(grammarAccess.getComplexSlotAccess().getConditionalSlotParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_ConditionalSlot_1=ruleConditionalSlot();

                    state._fsp--;


                    			current = this_ConditionalSlot_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComplexSlot"


    // $ANTLR start "entryRuleBooleanOperation"
    // InternalModelFragment.g:2100:1: entryRuleBooleanOperation returns [EObject current=null] : iv_ruleBooleanOperation= ruleBooleanOperation EOF ;
    public final EObject entryRuleBooleanOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanOperation = null;


        try {
            // InternalModelFragment.g:2100:57: (iv_ruleBooleanOperation= ruleBooleanOperation EOF )
            // InternalModelFragment.g:2101:2: iv_ruleBooleanOperation= ruleBooleanOperation EOF
            {
             newCompositeNode(grammarAccess.getBooleanOperationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBooleanOperation=ruleBooleanOperation();

            state._fsp--;

             current =iv_ruleBooleanOperation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBooleanOperation"


    // $ANTLR start "ruleBooleanOperation"
    // InternalModelFragment.g:2107:1: ruleBooleanOperation returns [EObject current=null] : (this_OrSlot_0= ruleOrSlot | this_NotSlot_1= ruleNotSlot | this_AndSlot_2= ruleAndSlot ) ;
    public final EObject ruleBooleanOperation() throws RecognitionException {
        EObject current = null;

        EObject this_OrSlot_0 = null;

        EObject this_NotSlot_1 = null;

        EObject this_AndSlot_2 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:2113:2: ( (this_OrSlot_0= ruleOrSlot | this_NotSlot_1= ruleNotSlot | this_AndSlot_2= ruleAndSlot ) )
            // InternalModelFragment.g:2114:2: (this_OrSlot_0= ruleOrSlot | this_NotSlot_1= ruleNotSlot | this_AndSlot_2= ruleAndSlot )
            {
            // InternalModelFragment.g:2114:2: (this_OrSlot_0= ruleOrSlot | this_NotSlot_1= ruleNotSlot | this_AndSlot_2= ruleAndSlot )
            int alt36=3;
            switch ( input.LA(1) ) {
            case 37:
                {
                alt36=1;
                }
                break;
            case 39:
                {
                alt36=2;
                }
                break;
            case 38:
                {
                alt36=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }

            switch (alt36) {
                case 1 :
                    // InternalModelFragment.g:2115:3: this_OrSlot_0= ruleOrSlot
                    {

                    			newCompositeNode(grammarAccess.getBooleanOperationAccess().getOrSlotParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_OrSlot_0=ruleOrSlot();

                    state._fsp--;


                    			current = this_OrSlot_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalModelFragment.g:2124:3: this_NotSlot_1= ruleNotSlot
                    {

                    			newCompositeNode(grammarAccess.getBooleanOperationAccess().getNotSlotParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_NotSlot_1=ruleNotSlot();

                    state._fsp--;


                    			current = this_NotSlot_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalModelFragment.g:2133:3: this_AndSlot_2= ruleAndSlot
                    {

                    			newCompositeNode(grammarAccess.getBooleanOperationAccess().getAndSlotParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_AndSlot_2=ruleAndSlot();

                    state._fsp--;


                    			current = this_AndSlot_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBooleanOperation"


    // $ANTLR start "entryRuleConditionalSlot"
    // InternalModelFragment.g:2145:1: entryRuleConditionalSlot returns [EObject current=null] : iv_ruleConditionalSlot= ruleConditionalSlot EOF ;
    public final EObject entryRuleConditionalSlot() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConditionalSlot = null;


        try {
            // InternalModelFragment.g:2145:56: (iv_ruleConditionalSlot= ruleConditionalSlot EOF )
            // InternalModelFragment.g:2146:2: iv_ruleConditionalSlot= ruleConditionalSlot EOF
            {
             newCompositeNode(grammarAccess.getConditionalSlotRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConditionalSlot=ruleConditionalSlot();

            state._fsp--;

             current =iv_ruleConditionalSlot; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConditionalSlot"


    // $ANTLR start "ruleConditionalSlot"
    // InternalModelFragment.g:2152:1: ruleConditionalSlot returns [EObject current=null] : ( ( ( (lv_name_0_1= RULE_ID | lv_name_0_2= ruleVARIABLE ) ) ) ( (lv_condition_1_0= ruleCondition ) ) ( (lv_value_2_0= ruleValue ) ) ) ;
    public final EObject ruleConditionalSlot() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_1=null;
        AntlrDatatypeRuleToken lv_name_0_2 = null;

        Enumerator lv_condition_1_0 = null;

        EObject lv_value_2_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:2158:2: ( ( ( ( (lv_name_0_1= RULE_ID | lv_name_0_2= ruleVARIABLE ) ) ) ( (lv_condition_1_0= ruleCondition ) ) ( (lv_value_2_0= ruleValue ) ) ) )
            // InternalModelFragment.g:2159:2: ( ( ( (lv_name_0_1= RULE_ID | lv_name_0_2= ruleVARIABLE ) ) ) ( (lv_condition_1_0= ruleCondition ) ) ( (lv_value_2_0= ruleValue ) ) )
            {
            // InternalModelFragment.g:2159:2: ( ( ( (lv_name_0_1= RULE_ID | lv_name_0_2= ruleVARIABLE ) ) ) ( (lv_condition_1_0= ruleCondition ) ) ( (lv_value_2_0= ruleValue ) ) )
            // InternalModelFragment.g:2160:3: ( ( (lv_name_0_1= RULE_ID | lv_name_0_2= ruleVARIABLE ) ) ) ( (lv_condition_1_0= ruleCondition ) ) ( (lv_value_2_0= ruleValue ) )
            {
            // InternalModelFragment.g:2160:3: ( ( (lv_name_0_1= RULE_ID | lv_name_0_2= ruleVARIABLE ) ) )
            // InternalModelFragment.g:2161:4: ( (lv_name_0_1= RULE_ID | lv_name_0_2= ruleVARIABLE ) )
            {
            // InternalModelFragment.g:2161:4: ( (lv_name_0_1= RULE_ID | lv_name_0_2= ruleVARIABLE ) )
            // InternalModelFragment.g:2162:5: (lv_name_0_1= RULE_ID | lv_name_0_2= ruleVARIABLE )
            {
            // InternalModelFragment.g:2162:5: (lv_name_0_1= RULE_ID | lv_name_0_2= ruleVARIABLE )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==RULE_ID) ) {
                alt37=1;
            }
            else if ( (LA37_0==36) ) {
                alt37=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // InternalModelFragment.g:2163:6: lv_name_0_1= RULE_ID
                    {
                    lv_name_0_1=(Token)match(input,RULE_ID,FOLLOW_34); 

                    						newLeafNode(lv_name_0_1, grammarAccess.getConditionalSlotAccess().getNameIDTerminalRuleCall_0_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionalSlotRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"name",
                    							lv_name_0_1,
                    							"org.jactr.io2.jactr.ModelFragment.ID");
                    					

                    }
                    break;
                case 2 :
                    // InternalModelFragment.g:2178:6: lv_name_0_2= ruleVARIABLE
                    {

                    						newCompositeNode(grammarAccess.getConditionalSlotAccess().getNameVARIABLEParserRuleCall_0_0_1());
                    					
                    pushFollow(FOLLOW_34);
                    lv_name_0_2=ruleVARIABLE();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getConditionalSlotRule());
                    						}
                    						set(
                    							current,
                    							"name",
                    							lv_name_0_2,
                    							"org.jactr.io2.jactr.ModelFragment.VARIABLE");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;

            }


            }


            }

            // InternalModelFragment.g:2196:3: ( (lv_condition_1_0= ruleCondition ) )
            // InternalModelFragment.g:2197:4: (lv_condition_1_0= ruleCondition )
            {
            // InternalModelFragment.g:2197:4: (lv_condition_1_0= ruleCondition )
            // InternalModelFragment.g:2198:5: lv_condition_1_0= ruleCondition
            {

            					newCompositeNode(grammarAccess.getConditionalSlotAccess().getConditionConditionEnumRuleCall_1_0());
            				
            pushFollow(FOLLOW_33);
            lv_condition_1_0=ruleCondition();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConditionalSlotRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_1_0,
            						"org.jactr.io2.jactr.ModelFragment.Condition");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalModelFragment.g:2215:3: ( (lv_value_2_0= ruleValue ) )
            // InternalModelFragment.g:2216:4: (lv_value_2_0= ruleValue )
            {
            // InternalModelFragment.g:2216:4: (lv_value_2_0= ruleValue )
            // InternalModelFragment.g:2217:5: lv_value_2_0= ruleValue
            {

            					newCompositeNode(grammarAccess.getConditionalSlotAccess().getValueValueParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_value_2_0=ruleValue();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConditionalSlotRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_2_0,
            						"org.jactr.io2.jactr.ModelFragment.Value");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConditionalSlot"


    // $ANTLR start "entryRuleOrSlot"
    // InternalModelFragment.g:2238:1: entryRuleOrSlot returns [EObject current=null] : iv_ruleOrSlot= ruleOrSlot EOF ;
    public final EObject entryRuleOrSlot() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrSlot = null;


        try {
            // InternalModelFragment.g:2238:47: (iv_ruleOrSlot= ruleOrSlot EOF )
            // InternalModelFragment.g:2239:2: iv_ruleOrSlot= ruleOrSlot EOF
            {
             newCompositeNode(grammarAccess.getOrSlotRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOrSlot=ruleOrSlot();

            state._fsp--;

             current =iv_ruleOrSlot; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOrSlot"


    // $ANTLR start "ruleOrSlot"
    // InternalModelFragment.g:2245:1: ruleOrSlot returns [EObject current=null] : ( () otherlv_1= 'or' otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' ) ;
    public final EObject ruleOrSlot() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_slots_3_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:2251:2: ( ( () otherlv_1= 'or' otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' ) )
            // InternalModelFragment.g:2252:2: ( () otherlv_1= 'or' otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' )
            {
            // InternalModelFragment.g:2252:2: ( () otherlv_1= 'or' otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' )
            // InternalModelFragment.g:2253:3: () otherlv_1= 'or' otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}'
            {
            // InternalModelFragment.g:2253:3: ()
            // InternalModelFragment.g:2254:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getOrSlotAccess().getOrSlotAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,37,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getOrSlotAccess().getOrKeyword_1());
            		
            otherlv_2=(Token)match(input,14,FOLLOW_29); 

            			newLeafNode(otherlv_2, grammarAccess.getOrSlotAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalModelFragment.g:2268:3: ( (lv_slots_3_0= ruleComplexSlot ) )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==RULE_ID||(LA38_0>=36 && LA38_0<=39)) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // InternalModelFragment.g:2269:4: (lv_slots_3_0= ruleComplexSlot )
            	    {
            	    // InternalModelFragment.g:2269:4: (lv_slots_3_0= ruleComplexSlot )
            	    // InternalModelFragment.g:2270:5: lv_slots_3_0= ruleComplexSlot
            	    {

            	    					newCompositeNode(grammarAccess.getOrSlotAccess().getSlotsComplexSlotParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_29);
            	    lv_slots_3_0=ruleComplexSlot();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getOrSlotRule());
            	    					}
            	    					add(
            	    						current,
            	    						"slots",
            	    						lv_slots_3_0,
            	    						"org.jactr.io2.jactr.ModelFragment.ComplexSlot");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);

            otherlv_4=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getOrSlotAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOrSlot"


    // $ANTLR start "entryRuleAndSlot"
    // InternalModelFragment.g:2295:1: entryRuleAndSlot returns [EObject current=null] : iv_ruleAndSlot= ruleAndSlot EOF ;
    public final EObject entryRuleAndSlot() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndSlot = null;


        try {
            // InternalModelFragment.g:2295:48: (iv_ruleAndSlot= ruleAndSlot EOF )
            // InternalModelFragment.g:2296:2: iv_ruleAndSlot= ruleAndSlot EOF
            {
             newCompositeNode(grammarAccess.getAndSlotRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAndSlot=ruleAndSlot();

            state._fsp--;

             current =iv_ruleAndSlot; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAndSlot"


    // $ANTLR start "ruleAndSlot"
    // InternalModelFragment.g:2302:1: ruleAndSlot returns [EObject current=null] : ( () otherlv_1= 'and' otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' ) ;
    public final EObject ruleAndSlot() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_slots_3_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:2308:2: ( ( () otherlv_1= 'and' otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' ) )
            // InternalModelFragment.g:2309:2: ( () otherlv_1= 'and' otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' )
            {
            // InternalModelFragment.g:2309:2: ( () otherlv_1= 'and' otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' )
            // InternalModelFragment.g:2310:3: () otherlv_1= 'and' otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}'
            {
            // InternalModelFragment.g:2310:3: ()
            // InternalModelFragment.g:2311:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getAndSlotAccess().getAndSlotAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,38,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getAndSlotAccess().getAndKeyword_1());
            		
            otherlv_2=(Token)match(input,14,FOLLOW_29); 

            			newLeafNode(otherlv_2, grammarAccess.getAndSlotAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalModelFragment.g:2325:3: ( (lv_slots_3_0= ruleComplexSlot ) )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==RULE_ID||(LA39_0>=36 && LA39_0<=39)) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalModelFragment.g:2326:4: (lv_slots_3_0= ruleComplexSlot )
            	    {
            	    // InternalModelFragment.g:2326:4: (lv_slots_3_0= ruleComplexSlot )
            	    // InternalModelFragment.g:2327:5: lv_slots_3_0= ruleComplexSlot
            	    {

            	    					newCompositeNode(grammarAccess.getAndSlotAccess().getSlotsComplexSlotParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_29);
            	    lv_slots_3_0=ruleComplexSlot();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getAndSlotRule());
            	    					}
            	    					add(
            	    						current,
            	    						"slots",
            	    						lv_slots_3_0,
            	    						"org.jactr.io2.jactr.ModelFragment.ComplexSlot");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);

            otherlv_4=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getAndSlotAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAndSlot"


    // $ANTLR start "entryRuleNotSlot"
    // InternalModelFragment.g:2352:1: entryRuleNotSlot returns [EObject current=null] : iv_ruleNotSlot= ruleNotSlot EOF ;
    public final EObject entryRuleNotSlot() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNotSlot = null;


        try {
            // InternalModelFragment.g:2352:48: (iv_ruleNotSlot= ruleNotSlot EOF )
            // InternalModelFragment.g:2353:2: iv_ruleNotSlot= ruleNotSlot EOF
            {
             newCompositeNode(grammarAccess.getNotSlotRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNotSlot=ruleNotSlot();

            state._fsp--;

             current =iv_ruleNotSlot; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNotSlot"


    // $ANTLR start "ruleNotSlot"
    // InternalModelFragment.g:2359:1: ruleNotSlot returns [EObject current=null] : ( () otherlv_1= 'not' otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' ) ;
    public final EObject ruleNotSlot() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_slots_3_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:2365:2: ( ( () otherlv_1= 'not' otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' ) )
            // InternalModelFragment.g:2366:2: ( () otherlv_1= 'not' otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' )
            {
            // InternalModelFragment.g:2366:2: ( () otherlv_1= 'not' otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}' )
            // InternalModelFragment.g:2367:3: () otherlv_1= 'not' otherlv_2= '{' ( (lv_slots_3_0= ruleComplexSlot ) )* otherlv_4= '}'
            {
            // InternalModelFragment.g:2367:3: ()
            // InternalModelFragment.g:2368:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getNotSlotAccess().getNotSlotAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,39,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getNotSlotAccess().getNotKeyword_1());
            		
            otherlv_2=(Token)match(input,14,FOLLOW_29); 

            			newLeafNode(otherlv_2, grammarAccess.getNotSlotAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalModelFragment.g:2382:3: ( (lv_slots_3_0= ruleComplexSlot ) )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==RULE_ID||(LA40_0>=36 && LA40_0<=39)) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // InternalModelFragment.g:2383:4: (lv_slots_3_0= ruleComplexSlot )
            	    {
            	    // InternalModelFragment.g:2383:4: (lv_slots_3_0= ruleComplexSlot )
            	    // InternalModelFragment.g:2384:5: lv_slots_3_0= ruleComplexSlot
            	    {

            	    					newCompositeNode(grammarAccess.getNotSlotAccess().getSlotsComplexSlotParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_29);
            	    lv_slots_3_0=ruleComplexSlot();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getNotSlotRule());
            	    					}
            	    					add(
            	    						current,
            	    						"slots",
            	    						lv_slots_3_0,
            	    						"org.jactr.io2.jactr.ModelFragment.ComplexSlot");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);

            otherlv_4=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getNotSlotAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNotSlot"


    // $ANTLR start "entryRuleParameter"
    // InternalModelFragment.g:2409:1: entryRuleParameter returns [EObject current=null] : iv_ruleParameter= ruleParameter EOF ;
    public final EObject entryRuleParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameter = null;


        try {
            // InternalModelFragment.g:2409:50: (iv_ruleParameter= ruleParameter EOF )
            // InternalModelFragment.g:2410:2: iv_ruleParameter= ruleParameter EOF
            {
             newCompositeNode(grammarAccess.getParameterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParameter=ruleParameter();

            state._fsp--;

             current =iv_ruleParameter; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameter"


    // $ANTLR start "ruleParameter"
    // InternalModelFragment.g:2416:1: ruleParameter returns [EObject current=null] : ( ( (lv_name_0_0= RULE_STRING ) ) otherlv_1= ':' ( (lv_value_2_0= ruleValue ) ) ) ;
    public final EObject ruleParameter() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        EObject lv_value_2_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:2422:2: ( ( ( (lv_name_0_0= RULE_STRING ) ) otherlv_1= ':' ( (lv_value_2_0= ruleValue ) ) ) )
            // InternalModelFragment.g:2423:2: ( ( (lv_name_0_0= RULE_STRING ) ) otherlv_1= ':' ( (lv_value_2_0= ruleValue ) ) )
            {
            // InternalModelFragment.g:2423:2: ( ( (lv_name_0_0= RULE_STRING ) ) otherlv_1= ':' ( (lv_value_2_0= ruleValue ) ) )
            // InternalModelFragment.g:2424:3: ( (lv_name_0_0= RULE_STRING ) ) otherlv_1= ':' ( (lv_value_2_0= ruleValue ) )
            {
            // InternalModelFragment.g:2424:3: ( (lv_name_0_0= RULE_STRING ) )
            // InternalModelFragment.g:2425:4: (lv_name_0_0= RULE_STRING )
            {
            // InternalModelFragment.g:2425:4: (lv_name_0_0= RULE_STRING )
            // InternalModelFragment.g:2426:5: lv_name_0_0= RULE_STRING
            {
            lv_name_0_0=(Token)match(input,RULE_STRING,FOLLOW_35); 

            					newLeafNode(lv_name_0_0, grammarAccess.getParameterAccess().getNameSTRINGTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getParameterRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_1=(Token)match(input,40,FOLLOW_33); 

            			newLeafNode(otherlv_1, grammarAccess.getParameterAccess().getColonKeyword_1());
            		
            // InternalModelFragment.g:2446:3: ( (lv_value_2_0= ruleValue ) )
            // InternalModelFragment.g:2447:4: (lv_value_2_0= ruleValue )
            {
            // InternalModelFragment.g:2447:4: (lv_value_2_0= ruleValue )
            // InternalModelFragment.g:2448:5: lv_value_2_0= ruleValue
            {

            					newCompositeNode(grammarAccess.getParameterAccess().getValueValueParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_value_2_0=ruleValue();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getParameterRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_2_0,
            						"org.jactr.io2.jactr.ModelFragment.Value");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameter"


    // $ANTLR start "entryRuleParametersBlock"
    // InternalModelFragment.g:2469:1: entryRuleParametersBlock returns [EObject current=null] : iv_ruleParametersBlock= ruleParametersBlock EOF ;
    public final EObject entryRuleParametersBlock() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParametersBlock = null;


        try {
            // InternalModelFragment.g:2469:56: (iv_ruleParametersBlock= ruleParametersBlock EOF )
            // InternalModelFragment.g:2470:2: iv_ruleParametersBlock= ruleParametersBlock EOF
            {
             newCompositeNode(grammarAccess.getParametersBlockRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParametersBlock=ruleParametersBlock();

            state._fsp--;

             current =iv_ruleParametersBlock; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParametersBlock"


    // $ANTLR start "ruleParametersBlock"
    // InternalModelFragment.g:2476:1: ruleParametersBlock returns [EObject current=null] : ( () otherlv_1= '[' ( (lv_parameter_2_0= ruleParameter ) )* otherlv_3= ']' ) ;
    public final EObject ruleParametersBlock() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_parameter_2_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:2482:2: ( ( () otherlv_1= '[' ( (lv_parameter_2_0= ruleParameter ) )* otherlv_3= ']' ) )
            // InternalModelFragment.g:2483:2: ( () otherlv_1= '[' ( (lv_parameter_2_0= ruleParameter ) )* otherlv_3= ']' )
            {
            // InternalModelFragment.g:2483:2: ( () otherlv_1= '[' ( (lv_parameter_2_0= ruleParameter ) )* otherlv_3= ']' )
            // InternalModelFragment.g:2484:3: () otherlv_1= '[' ( (lv_parameter_2_0= ruleParameter ) )* otherlv_3= ']'
            {
            // InternalModelFragment.g:2484:3: ()
            // InternalModelFragment.g:2485:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getParametersBlockAccess().getParametersBlockAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,41,FOLLOW_36); 

            			newLeafNode(otherlv_1, grammarAccess.getParametersBlockAccess().getLeftSquareBracketKeyword_1());
            		
            // InternalModelFragment.g:2495:3: ( (lv_parameter_2_0= ruleParameter ) )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==RULE_STRING) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // InternalModelFragment.g:2496:4: (lv_parameter_2_0= ruleParameter )
            	    {
            	    // InternalModelFragment.g:2496:4: (lv_parameter_2_0= ruleParameter )
            	    // InternalModelFragment.g:2497:5: lv_parameter_2_0= ruleParameter
            	    {

            	    					newCompositeNode(grammarAccess.getParametersBlockAccess().getParameterParameterParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_36);
            	    lv_parameter_2_0=ruleParameter();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getParametersBlockRule());
            	    					}
            	    					add(
            	    						current,
            	    						"parameter",
            	    						lv_parameter_2_0,
            	    						"org.jactr.io2.jactr.ModelFragment.Parameter");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);

            otherlv_3=(Token)match(input,42,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getParametersBlockAccess().getRightSquareBracketKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParametersBlock"


    // $ANTLR start "entryRuleValue"
    // InternalModelFragment.g:2522:1: entryRuleValue returns [EObject current=null] : iv_ruleValue= ruleValue EOF ;
    public final EObject entryRuleValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValue = null;


        try {
            // InternalModelFragment.g:2522:46: (iv_ruleValue= ruleValue EOF )
            // InternalModelFragment.g:2523:2: iv_ruleValue= ruleValue EOF
            {
             newCompositeNode(grammarAccess.getValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleValue=ruleValue();

            state._fsp--;

             current =iv_ruleValue; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValue"


    // $ANTLR start "ruleValue"
    // InternalModelFragment.g:2529:1: ruleValue returns [EObject current=null] : ( ( () ( ( (lv_name_1_1= ruleVARIABLE | lv_name_1_2= RULE_ID | lv_name_1_3= ruleNULL ) ) ) ) | ( (lv_string_2_0= RULE_STRING ) ) | ( (lv_number_3_0= ruleNUMERIC ) ) | ( (lv_boolean_4_0= ruleBOOLEAN ) ) ) ;
    public final EObject ruleValue() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_2=null;
        Token lv_string_2_0=null;
        AntlrDatatypeRuleToken lv_name_1_1 = null;

        AntlrDatatypeRuleToken lv_name_1_3 = null;

        AntlrDatatypeRuleToken lv_number_3_0 = null;

        AntlrDatatypeRuleToken lv_boolean_4_0 = null;



        	enterRule();

        try {
            // InternalModelFragment.g:2535:2: ( ( ( () ( ( (lv_name_1_1= ruleVARIABLE | lv_name_1_2= RULE_ID | lv_name_1_3= ruleNULL ) ) ) ) | ( (lv_string_2_0= RULE_STRING ) ) | ( (lv_number_3_0= ruleNUMERIC ) ) | ( (lv_boolean_4_0= ruleBOOLEAN ) ) ) )
            // InternalModelFragment.g:2536:2: ( ( () ( ( (lv_name_1_1= ruleVARIABLE | lv_name_1_2= RULE_ID | lv_name_1_3= ruleNULL ) ) ) ) | ( (lv_string_2_0= RULE_STRING ) ) | ( (lv_number_3_0= ruleNUMERIC ) ) | ( (lv_boolean_4_0= ruleBOOLEAN ) ) )
            {
            // InternalModelFragment.g:2536:2: ( ( () ( ( (lv_name_1_1= ruleVARIABLE | lv_name_1_2= RULE_ID | lv_name_1_3= ruleNULL ) ) ) ) | ( (lv_string_2_0= RULE_STRING ) ) | ( (lv_number_3_0= ruleNUMERIC ) ) | ( (lv_boolean_4_0= ruleBOOLEAN ) ) )
            int alt43=4;
            switch ( input.LA(1) ) {
            case RULE_ID:
            case 36:
            case 43:
                {
                alt43=1;
                }
                break;
            case RULE_STRING:
                {
                alt43=2;
                }
                break;
            case RULE_DIGITS:
            case 33:
                {
                alt43=3;
                }
                break;
            case 44:
            case 45:
                {
                alt43=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }

            switch (alt43) {
                case 1 :
                    // InternalModelFragment.g:2537:3: ( () ( ( (lv_name_1_1= ruleVARIABLE | lv_name_1_2= RULE_ID | lv_name_1_3= ruleNULL ) ) ) )
                    {
                    // InternalModelFragment.g:2537:3: ( () ( ( (lv_name_1_1= ruleVARIABLE | lv_name_1_2= RULE_ID | lv_name_1_3= ruleNULL ) ) ) )
                    // InternalModelFragment.g:2538:4: () ( ( (lv_name_1_1= ruleVARIABLE | lv_name_1_2= RULE_ID | lv_name_1_3= ruleNULL ) ) )
                    {
                    // InternalModelFragment.g:2538:4: ()
                    // InternalModelFragment.g:2539:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getValueAccess().getValueAction_0_0(),
                    						current);
                    				

                    }

                    // InternalModelFragment.g:2545:4: ( ( (lv_name_1_1= ruleVARIABLE | lv_name_1_2= RULE_ID | lv_name_1_3= ruleNULL ) ) )
                    // InternalModelFragment.g:2546:5: ( (lv_name_1_1= ruleVARIABLE | lv_name_1_2= RULE_ID | lv_name_1_3= ruleNULL ) )
                    {
                    // InternalModelFragment.g:2546:5: ( (lv_name_1_1= ruleVARIABLE | lv_name_1_2= RULE_ID | lv_name_1_3= ruleNULL ) )
                    // InternalModelFragment.g:2547:6: (lv_name_1_1= ruleVARIABLE | lv_name_1_2= RULE_ID | lv_name_1_3= ruleNULL )
                    {
                    // InternalModelFragment.g:2547:6: (lv_name_1_1= ruleVARIABLE | lv_name_1_2= RULE_ID | lv_name_1_3= ruleNULL )
                    int alt42=3;
                    switch ( input.LA(1) ) {
                    case 36:
                        {
                        alt42=1;
                        }
                        break;
                    case RULE_ID:
                        {
                        alt42=2;
                        }
                        break;
                    case 43:
                        {
                        alt42=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 42, 0, input);

                        throw nvae;
                    }

                    switch (alt42) {
                        case 1 :
                            // InternalModelFragment.g:2548:7: lv_name_1_1= ruleVARIABLE
                            {

                            							newCompositeNode(grammarAccess.getValueAccess().getNameVARIABLEParserRuleCall_0_1_0_0());
                            						
                            pushFollow(FOLLOW_2);
                            lv_name_1_1=ruleVARIABLE();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getValueRule());
                            							}
                            							set(
                            								current,
                            								"name",
                            								lv_name_1_1,
                            								"org.jactr.io2.jactr.ModelFragment.VARIABLE");
                            							afterParserOrEnumRuleCall();
                            						

                            }
                            break;
                        case 2 :
                            // InternalModelFragment.g:2564:7: lv_name_1_2= RULE_ID
                            {
                            lv_name_1_2=(Token)match(input,RULE_ID,FOLLOW_2); 

                            							newLeafNode(lv_name_1_2, grammarAccess.getValueAccess().getNameIDTerminalRuleCall_0_1_0_1());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getValueRule());
                            							}
                            							setWithLastConsumed(
                            								current,
                            								"name",
                            								lv_name_1_2,
                            								"org.jactr.io2.jactr.ModelFragment.ID");
                            						

                            }
                            break;
                        case 3 :
                            // InternalModelFragment.g:2579:7: lv_name_1_3= ruleNULL
                            {

                            							newCompositeNode(grammarAccess.getValueAccess().getNameNULLParserRuleCall_0_1_0_2());
                            						
                            pushFollow(FOLLOW_2);
                            lv_name_1_3=ruleNULL();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getValueRule());
                            							}
                            							set(
                            								current,
                            								"name",
                            								lv_name_1_3,
                            								"org.jactr.io2.jactr.ModelFragment.NULL");
                            							afterParserOrEnumRuleCall();
                            						

                            }
                            break;

                    }


                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalModelFragment.g:2599:3: ( (lv_string_2_0= RULE_STRING ) )
                    {
                    // InternalModelFragment.g:2599:3: ( (lv_string_2_0= RULE_STRING ) )
                    // InternalModelFragment.g:2600:4: (lv_string_2_0= RULE_STRING )
                    {
                    // InternalModelFragment.g:2600:4: (lv_string_2_0= RULE_STRING )
                    // InternalModelFragment.g:2601:5: lv_string_2_0= RULE_STRING
                    {
                    lv_string_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    					newLeafNode(lv_string_2_0, grammarAccess.getValueAccess().getStringSTRINGTerminalRuleCall_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getValueRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"string",
                    						lv_string_2_0,
                    						"org.eclipse.xtext.common.Terminals.STRING");
                    				

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalModelFragment.g:2618:3: ( (lv_number_3_0= ruleNUMERIC ) )
                    {
                    // InternalModelFragment.g:2618:3: ( (lv_number_3_0= ruleNUMERIC ) )
                    // InternalModelFragment.g:2619:4: (lv_number_3_0= ruleNUMERIC )
                    {
                    // InternalModelFragment.g:2619:4: (lv_number_3_0= ruleNUMERIC )
                    // InternalModelFragment.g:2620:5: lv_number_3_0= ruleNUMERIC
                    {

                    					newCompositeNode(grammarAccess.getValueAccess().getNumberNUMERICParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_number_3_0=ruleNUMERIC();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getValueRule());
                    					}
                    					set(
                    						current,
                    						"number",
                    						lv_number_3_0,
                    						"org.jactr.io2.jactr.ModelFragment.NUMERIC");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalModelFragment.g:2638:3: ( (lv_boolean_4_0= ruleBOOLEAN ) )
                    {
                    // InternalModelFragment.g:2638:3: ( (lv_boolean_4_0= ruleBOOLEAN ) )
                    // InternalModelFragment.g:2639:4: (lv_boolean_4_0= ruleBOOLEAN )
                    {
                    // InternalModelFragment.g:2639:4: (lv_boolean_4_0= ruleBOOLEAN )
                    // InternalModelFragment.g:2640:5: lv_boolean_4_0= ruleBOOLEAN
                    {

                    					newCompositeNode(grammarAccess.getValueAccess().getBooleanBOOLEANParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_boolean_4_0=ruleBOOLEAN();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getValueRule());
                    					}
                    					set(
                    						current,
                    						"boolean",
                    						lv_boolean_4_0,
                    						"org.jactr.io2.jactr.ModelFragment.BOOLEAN");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValue"


    // $ANTLR start "entryRuleVARIABLE"
    // InternalModelFragment.g:2661:1: entryRuleVARIABLE returns [String current=null] : iv_ruleVARIABLE= ruleVARIABLE EOF ;
    public final String entryRuleVARIABLE() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleVARIABLE = null;


        try {
            // InternalModelFragment.g:2661:48: (iv_ruleVARIABLE= ruleVARIABLE EOF )
            // InternalModelFragment.g:2662:2: iv_ruleVARIABLE= ruleVARIABLE EOF
            {
             newCompositeNode(grammarAccess.getVARIABLERule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVARIABLE=ruleVARIABLE();

            state._fsp--;

             current =iv_ruleVARIABLE.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVARIABLE"


    // $ANTLR start "ruleVARIABLE"
    // InternalModelFragment.g:2668:1: ruleVARIABLE returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '=' this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleVARIABLE() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_ID_1=null;


        	enterRule();

        try {
            // InternalModelFragment.g:2674:2: ( (kw= '=' this_ID_1= RULE_ID ) )
            // InternalModelFragment.g:2675:2: (kw= '=' this_ID_1= RULE_ID )
            {
            // InternalModelFragment.g:2675:2: (kw= '=' this_ID_1= RULE_ID )
            // InternalModelFragment.g:2676:3: kw= '=' this_ID_1= RULE_ID
            {
            kw=(Token)match(input,36,FOLLOW_3); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getVARIABLEAccess().getEqualsSignKeyword_0());
            		
            this_ID_1=(Token)match(input,RULE_ID,FOLLOW_2); 

            			current.merge(this_ID_1);
            		

            			newLeafNode(this_ID_1, grammarAccess.getVARIABLEAccess().getIDTerminalRuleCall_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVARIABLE"


    // $ANTLR start "entryRuleNULL"
    // InternalModelFragment.g:2692:1: entryRuleNULL returns [String current=null] : iv_ruleNULL= ruleNULL EOF ;
    public final String entryRuleNULL() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNULL = null;


        try {
            // InternalModelFragment.g:2692:44: (iv_ruleNULL= ruleNULL EOF )
            // InternalModelFragment.g:2693:2: iv_ruleNULL= ruleNULL EOF
            {
             newCompositeNode(grammarAccess.getNULLRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNULL=ruleNULL();

            state._fsp--;

             current =iv_ruleNULL.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNULL"


    // $ANTLR start "ruleNULL"
    // InternalModelFragment.g:2699:1: ruleNULL returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= 'null' ;
    public final AntlrDatatypeRuleToken ruleNULL() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalModelFragment.g:2705:2: (kw= 'null' )
            // InternalModelFragment.g:2706:2: kw= 'null'
            {
            kw=(Token)match(input,43,FOLLOW_2); 

            		current.merge(kw);
            		newLeafNode(kw, grammarAccess.getNULLAccess().getNullKeyword());
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNULL"


    // $ANTLR start "entryRuleBOOLEAN"
    // InternalModelFragment.g:2714:1: entryRuleBOOLEAN returns [String current=null] : iv_ruleBOOLEAN= ruleBOOLEAN EOF ;
    public final String entryRuleBOOLEAN() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBOOLEAN = null;


        try {
            // InternalModelFragment.g:2714:47: (iv_ruleBOOLEAN= ruleBOOLEAN EOF )
            // InternalModelFragment.g:2715:2: iv_ruleBOOLEAN= ruleBOOLEAN EOF
            {
             newCompositeNode(grammarAccess.getBOOLEANRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBOOLEAN=ruleBOOLEAN();

            state._fsp--;

             current =iv_ruleBOOLEAN.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBOOLEAN"


    // $ANTLR start "ruleBOOLEAN"
    // InternalModelFragment.g:2721:1: ruleBOOLEAN returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'true' | kw= 'false' ) ;
    public final AntlrDatatypeRuleToken ruleBOOLEAN() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalModelFragment.g:2727:2: ( (kw= 'true' | kw= 'false' ) )
            // InternalModelFragment.g:2728:2: (kw= 'true' | kw= 'false' )
            {
            // InternalModelFragment.g:2728:2: (kw= 'true' | kw= 'false' )
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==44) ) {
                alt44=1;
            }
            else if ( (LA44_0==45) ) {
                alt44=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;
            }
            switch (alt44) {
                case 1 :
                    // InternalModelFragment.g:2729:3: kw= 'true'
                    {
                    kw=(Token)match(input,44,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getBOOLEANAccess().getTrueKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalModelFragment.g:2735:3: kw= 'false'
                    {
                    kw=(Token)match(input,45,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getBOOLEANAccess().getFalseKeyword_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBOOLEAN"


    // $ANTLR start "entryRuleNUMERIC"
    // InternalModelFragment.g:2744:1: entryRuleNUMERIC returns [String current=null] : iv_ruleNUMERIC= ruleNUMERIC EOF ;
    public final String entryRuleNUMERIC() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNUMERIC = null;


        try {
            // InternalModelFragment.g:2744:47: (iv_ruleNUMERIC= ruleNUMERIC EOF )
            // InternalModelFragment.g:2745:2: iv_ruleNUMERIC= ruleNUMERIC EOF
            {
             newCompositeNode(grammarAccess.getNUMERICRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNUMERIC=ruleNUMERIC();

            state._fsp--;

             current =iv_ruleNUMERIC.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNUMERIC"


    // $ANTLR start "ruleNUMERIC"
    // InternalModelFragment.g:2751:1: ruleNUMERIC returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_DIGITS_1= RULE_DIGITS (kw= '.' this_DIGITS_3= RULE_DIGITS )? ) ;
    public final AntlrDatatypeRuleToken ruleNUMERIC() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_DIGITS_1=null;
        Token this_DIGITS_3=null;


        	enterRule();

        try {
            // InternalModelFragment.g:2757:2: ( ( (kw= '-' )? this_DIGITS_1= RULE_DIGITS (kw= '.' this_DIGITS_3= RULE_DIGITS )? ) )
            // InternalModelFragment.g:2758:2: ( (kw= '-' )? this_DIGITS_1= RULE_DIGITS (kw= '.' this_DIGITS_3= RULE_DIGITS )? )
            {
            // InternalModelFragment.g:2758:2: ( (kw= '-' )? this_DIGITS_1= RULE_DIGITS (kw= '.' this_DIGITS_3= RULE_DIGITS )? )
            // InternalModelFragment.g:2759:3: (kw= '-' )? this_DIGITS_1= RULE_DIGITS (kw= '.' this_DIGITS_3= RULE_DIGITS )?
            {
            // InternalModelFragment.g:2759:3: (kw= '-' )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==33) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // InternalModelFragment.g:2760:4: kw= '-'
                    {
                    kw=(Token)match(input,33,FOLLOW_37); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getNUMERICAccess().getHyphenMinusKeyword_0());
                    			

                    }
                    break;

            }

            this_DIGITS_1=(Token)match(input,RULE_DIGITS,FOLLOW_21); 

            			current.merge(this_DIGITS_1);
            		

            			newLeafNode(this_DIGITS_1, grammarAccess.getNUMERICAccess().getDIGITSTerminalRuleCall_1());
            		
            // InternalModelFragment.g:2773:3: (kw= '.' this_DIGITS_3= RULE_DIGITS )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==26) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // InternalModelFragment.g:2774:4: kw= '.' this_DIGITS_3= RULE_DIGITS
                    {
                    kw=(Token)match(input,26,FOLLOW_37); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getNUMERICAccess().getFullStopKeyword_2_0());
                    			
                    this_DIGITS_3=(Token)match(input,RULE_DIGITS,FOLLOW_2); 

                    				current.merge(this_DIGITS_3);
                    			

                    				newLeafNode(this_DIGITS_3, grammarAccess.getNUMERICAccess().getDIGITSTerminalRuleCall_2_1());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNUMERIC"


    // $ANTLR start "entryRuleOVERRIDE"
    // InternalModelFragment.g:2791:1: entryRuleOVERRIDE returns [String current=null] : iv_ruleOVERRIDE= ruleOVERRIDE EOF ;
    public final String entryRuleOVERRIDE() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOVERRIDE = null;


        try {
            // InternalModelFragment.g:2791:48: (iv_ruleOVERRIDE= ruleOVERRIDE EOF )
            // InternalModelFragment.g:2792:2: iv_ruleOVERRIDE= ruleOVERRIDE EOF
            {
             newCompositeNode(grammarAccess.getOVERRIDERule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOVERRIDE=ruleOVERRIDE();

            state._fsp--;

             current =iv_ruleOVERRIDE.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOVERRIDE"


    // $ANTLR start "ruleOVERRIDE"
    // InternalModelFragment.g:2798:1: ruleOVERRIDE returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '@Override' ;
    public final AntlrDatatypeRuleToken ruleOVERRIDE() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalModelFragment.g:2804:2: (kw= '@Override' )
            // InternalModelFragment.g:2805:2: kw= '@Override'
            {
            kw=(Token)match(input,46,FOLLOW_2); 

            		current.merge(kw);
            		newLeafNode(kw, grammarAccess.getOVERRIDEAccess().getOverrideKeyword());
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOVERRIDE"


    // $ANTLR start "ruleCondition"
    // InternalModelFragment.g:2813:1: ruleCondition returns [Enumerator current=null] : ( (enumLiteral_0= '=' ) | (enumLiteral_1= '<' ) | (enumLiteral_2= '<=' ) | (enumLiteral_3= '>' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '!=' ) ) ;
    public final Enumerator ruleCondition() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;


        	enterRule();

        try {
            // InternalModelFragment.g:2819:2: ( ( (enumLiteral_0= '=' ) | (enumLiteral_1= '<' ) | (enumLiteral_2= '<=' ) | (enumLiteral_3= '>' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '!=' ) ) )
            // InternalModelFragment.g:2820:2: ( (enumLiteral_0= '=' ) | (enumLiteral_1= '<' ) | (enumLiteral_2= '<=' ) | (enumLiteral_3= '>' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '!=' ) )
            {
            // InternalModelFragment.g:2820:2: ( (enumLiteral_0= '=' ) | (enumLiteral_1= '<' ) | (enumLiteral_2= '<=' ) | (enumLiteral_3= '>' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '!=' ) )
            int alt47=6;
            switch ( input.LA(1) ) {
            case 36:
                {
                alt47=1;
                }
                break;
            case 47:
                {
                alt47=2;
                }
                break;
            case 48:
                {
                alt47=3;
                }
                break;
            case 49:
                {
                alt47=4;
                }
                break;
            case 50:
                {
                alt47=5;
                }
                break;
            case 51:
                {
                alt47=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;
            }

            switch (alt47) {
                case 1 :
                    // InternalModelFragment.g:2821:3: (enumLiteral_0= '=' )
                    {
                    // InternalModelFragment.g:2821:3: (enumLiteral_0= '=' )
                    // InternalModelFragment.g:2822:4: enumLiteral_0= '='
                    {
                    enumLiteral_0=(Token)match(input,36,FOLLOW_2); 

                    				current = grammarAccess.getConditionAccess().getEQUALSEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getConditionAccess().getEQUALSEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalModelFragment.g:2829:3: (enumLiteral_1= '<' )
                    {
                    // InternalModelFragment.g:2829:3: (enumLiteral_1= '<' )
                    // InternalModelFragment.g:2830:4: enumLiteral_1= '<'
                    {
                    enumLiteral_1=(Token)match(input,47,FOLLOW_2); 

                    				current = grammarAccess.getConditionAccess().getLTEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getConditionAccess().getLTEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalModelFragment.g:2837:3: (enumLiteral_2= '<=' )
                    {
                    // InternalModelFragment.g:2837:3: (enumLiteral_2= '<=' )
                    // InternalModelFragment.g:2838:4: enumLiteral_2= '<='
                    {
                    enumLiteral_2=(Token)match(input,48,FOLLOW_2); 

                    				current = grammarAccess.getConditionAccess().getLTEEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getConditionAccess().getLTEEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalModelFragment.g:2845:3: (enumLiteral_3= '>' )
                    {
                    // InternalModelFragment.g:2845:3: (enumLiteral_3= '>' )
                    // InternalModelFragment.g:2846:4: enumLiteral_3= '>'
                    {
                    enumLiteral_3=(Token)match(input,49,FOLLOW_2); 

                    				current = grammarAccess.getConditionAccess().getGTEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getConditionAccess().getGTEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalModelFragment.g:2853:3: (enumLiteral_4= '>=' )
                    {
                    // InternalModelFragment.g:2853:3: (enumLiteral_4= '>=' )
                    // InternalModelFragment.g:2854:4: enumLiteral_4= '>='
                    {
                    enumLiteral_4=(Token)match(input,50,FOLLOW_2); 

                    				current = grammarAccess.getConditionAccess().getGTEEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getConditionAccess().getGTEEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalModelFragment.g:2861:3: (enumLiteral_5= '!=' )
                    {
                    // InternalModelFragment.g:2861:3: (enumLiteral_5= '!=' )
                    // InternalModelFragment.g:2862:4: enumLiteral_5= '!='
                    {
                    enumLiteral_5=(Token)match(input,51,FOLLOW_2); 

                    				current = grammarAccess.getConditionAccess().getNOTEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getConditionAccess().getNOTEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCondition"

    // Delegated rules


    protected DFA31 dfa31 = new DFA31(this);
    static final String dfa_1s = "\12\uffff";
    static final String dfa_2s = "\1\5\1\uffff\2\5\1\uffff\1\4\2\5\1\4\1\5";
    static final String dfa_3s = "\1\44\1\uffff\1\63\1\5\1\uffff\1\55\2\63\1\55\1\63";
    static final String dfa_4s = "\1\uffff\1\1\2\uffff\1\2\5\uffff";
    static final String dfa_5s = "\12\uffff}>";
    static final String[] dfa_6s = {
            "\1\2\11\uffff\1\4\23\uffff\1\1\1\3",
            "",
            "\1\1\11\uffff\1\1\12\uffff\1\1\11\uffff\1\5\12\uffff\5\4",
            "\1\6",
            "",
            "\1\4\1\7\1\uffff\1\4\31\uffff\1\4\2\uffff\1\4\6\uffff\3\4",
            "\1\1\11\uffff\1\1\24\uffff\1\5\12\uffff\5\4",
            "\1\4\11\uffff\1\4\24\uffff\1\10\12\uffff\5\1",
            "\1\1\1\11\1\uffff\1\1\31\uffff\1\1\2\uffff\1\1\6\uffff\3\1",
            "\1\1\11\uffff\1\1\24\uffff\1\5\12\uffff\5\4"
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA31 extends DFA {

        public DFA31(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 31;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "1716:3: ( (lv_isa_3_0= ruleIsaBlock ) )?";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x00004000188F8020L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x00004000188E8020L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x00004000188C8020L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000400010888020L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000400000000020L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000020000100002L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000001004000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000008020L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000400000008020L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x00000000E0000020L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x00000000E0008020L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x00000007E0000020L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x00000007E0008020L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000001800000020L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x000000F800008020L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000381A000000B0L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x000F801000000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000040000000010L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000000000080L});

}