package org.jactr.io2.jactr.ui.wizard

class ModelStubs {
	
	
 
  
  static def basicModel(String name){
  '''
/*
 * The namespace defines the location of your model in a global scope.
 * This is used when embedding other model content within a model
 */
 namespace «name»{
/*
 * These are the namespaces for common types and buffers. 
 */	
 import jactr.declarative.*
 import jactr.procedural.*
 import jactr.retrieval.*
 import jactr.imaginal.*
 import jactr.goal.*
/*
 * modules define theoretically relevant contributions to the model. A 
 * declarative and procedural module are the bare minimum. Note: parameters
 * are denoted by [].
 */
 module "org.jactr.core.module.declarative.six.DefaultDeclarativeModule6" [
   "ActivationNoise"          : "0"
   "BaseLevelConstant"        : "0"
   "EnablePartialMatching"    : "false"
   "MaximumDifference"        : "-1"
   "MaximumSimilarity"        : "0"
   "MismatchPenalty"          : "1"
   "PermanentActivationNoise" : "0"]
 module "org.jactr.core.module.procedural.six.DefaultProceduralModule6" [
   "DefaultProductionFiringTime" : "0.05"
   "ExpectedUtilityNoise"        : "0"
   "NumberOfProductionsFired"    : "0" ]
 module "org.jactr.core.module.goal.six.DefaultGoalModule6" [ ]
 module "org.jactr.core.module.retrieval.six.DefaultRetrievalModule6" [
   "EnableIndexedRetrievals" : "false"
   "FINSTDurationTime"       : "3.0"
   "LatencyFactor"           : "1"
   "NumberOfFINSTs"          : "4"
   "RetrievalThreshold"      : "0" ]
 module "org.jactr.core.module.random.six.DefaultRandomModule"[]   
   
 /**
  * common buffers. They are @overridden because we are telling the compiler to
  * use this particular definition and not the ones in jactr.declarative and
  * jactr.retrieval
  */  
 buffers {
   @Override
   goal {} [
     "Activation"              : "1"
     "StrictHarvestingEnabled" : "false" ]
 }

/*
 * chunks and types
 */ 
 
/*
 * productions
 */  
 
}[
   "EnablePersistentExecution" : "false"
   "EnableUnusedCycleSkipping" : "true" //safe for internal-only models
 ]
'''
  }
  
  static def basicModelDependencies(){
    #["org.jactr.core","org.jactr.io","org.jactr.tools","org.jactr.tools.experiment", "org.jactr.io2.jactr"]
  }
  
  static def embodiedModelDependencies(){
    #["org.jactr.core","org.jactr.io","org.jactr.tools","org.jactr.tools.experiment", "org.jactr.io2.jactr",
      "org.commonreality.api","org.commonreality.sensors","org.commonreality.sensors.xml"
    ]
  }
  
  static def embodiedModel(String name){'''
/*
 * 
 */
namespace «name»{
 import jactr.declarative.*
 import jactr.procedural.*
 import jactr.retrieval.*
 import jactr.imaginal.*
 import jactr.visual.*
 import jactr.motor.*
 import jactr.aural.*
 import jactr.vocal.*
 import jactr.goal.*
    
 module "org.jactr.core.module.declarative.six.DefaultDeclarativeModule6" [
   "ActivationNoise"          : "0"
   "BaseLevelConstant"        : "0"
   "EnablePartialMatching"    : "false"
   "MaximumDifference"        : "-1"
   "MaximumSimilarity"        : "0"
   "MismatchPenalty"          : "1"
   "PermanentActivationNoise" : "0"]
 module "org.jactr.core.module.procedural.six.DefaultProceduralModule6" [
   "DefaultProductionFiringTime" : "0.05"
   "ExpectedUtilityNoise"        : "0"
   "NumberOfProductionsFired"    : "0" ]
 module "org.jactr.core.module.goal.six.DefaultGoalModule6" []
 module "org.jactr.core.module.retrieval.six.DefaultRetrievalModule6" [
   "FINSTDurationTime"       : "3.0"
   "LatencyFactor"           : "1"
   "NumberOfFINSTs"          : "4"
   "RetrievalThreshold"      : "0" ]
module "org.jactr.core.module.imaginal.six.DefaultImaginalModule6"[]
module "org.jactr.modules.pm.visual.six.DefaultVisualModule6"[]
module "org.jactr.modules.pm.aural.six.DefaultAuralModule6"[]
module "org.jactr.modules.pm.vocal.six.DefaultVocalModule6"[]
module "org.jactr.modules.pm.motor.six.DefaultMotorModule6"[]
module "org.jactr.core.module.random.six.DefaultRandomModule"[]
   
 buffers {
   @Override
   goal {} [
     "Activation"              : "1"
     "StrictHarvestingEnabled" : "false" ]
 }
 }[
   "EnablePersistentExecution" : "true"
   "EnableUnusedCycleSkipping" : "false"
 ]
      '''    
  }
}