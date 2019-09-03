job('catalog-service') {

    configure { project ->
        project / 'customWorkspace' << '${JENKINS_HOME}/workspace/CATALOG_SERVICE_DSL'
    }

    scm {
        git('https://github.com/marzelwidmer/catalog-service.git')
    }


    steps {
        maven('-e clean package')


//         oc new-app fabric8/s2i-java:latest-java11~https://github.com/marzelwidmer/catalog-service.git; oc expose svc/catalog-service; oc get route catalog-service
//         shell('oc new-app fabric8/s2i-java:latest-java11~/tmp/nocontent --name=catalog-service')
//         shell('oc start-build catalog-service --from-file=Presentation/target/ROOT.war')
//         shell('oc expose service presentation --hostname=msa.example.com')

    }
}

job('CATALOG_SERVICE_DSLL_redeploy_catalog_service') {
    configure { project ->
        project / 'customWorkspace' << '${JENKINS_HOME}/workspace/CATALOG_SERVICE_DSL'
    }


    scm {
        git('https://github.com/marzelwidmer/catalog-service.git')
    }

    steps {
        maven('-e clean package')
//         shell('oc start-build billing-service --from-file=Billing/target/billing.war')
    }
}


// job('MSA_DSL_redeploy_product') {
//     configure { project ->
//         project / 'customWorkspace' << '${JENKINS_HOME}/workspace/MSA_DSL'
//     }
//
//
//     scm {
//         git('git://github.com/RHsyseng/MSA-EAP7-OSE.git')
//     }
//
//     steps {
//         maven('-e clean package')
//         shell('mkdir Product/target/deploy')
//         shell('cp Product/target/product.war Product/target/deploy')
//         shell('cp -r Product/configuration Product/target/deploy')
//         shell('oc start-build product-service --from-dir=Product/target/deploy')
//
//     }
// }
//
//
// job('MSA_DSL_redeploy_sales') {
//     configure { project ->
//         project / 'customWorkspace' << '${JENKINS_HOME}/workspace/MSA_DSL'
//     }
//
//
//     scm {
//         git('git://github.com/RHsyseng/MSA-EAP7-OSE.git')
//     }
//
//     steps {
//         maven('-e clean package')
//         shell('mkdir Sales/target/deploy')
//         shell('cp Sales/target/sales.war Sales/target/deploy')
//         shell('cp -r Sales/configuration Sales/target/deploy')
//         shell('oc start-build sales-service --from-dir=Sales/target/deploy')
//     }
// }

// job('MSA_DSL_redeploy_presentation') {
//     configure { project ->
//         project / 'customWorkspace' << '${JENKINS_HOME}/workspace/MSA_DSL'
//     }
//
//
//     scm {
//         git('git://github.com/RHsyseng/MSA-EAP7-OSE.git')
//     }
//
//     steps {
//         maven('-e clean package')
//         shell('oc start-build presentation --from-file=Presentation/target/ROOT.war')
//     }
// }



// def project=""
// def application="catalog-service"
// def dev_project="development"
// def test_project="testing"
// def prod_project="production"
// node {
//     stage('Init') {
//         project = env.PROJECT_NAME
//     }
//     stage('Build') {
//         echo "Build"
//         openshift.withCluster() {
//             openshift.withProject() {
//                 def builds = openshift.startBuild("$application")
//                 builds.logs('-f')
//                 timeout(5) {
//                     builds.untilEach(1) {
//                     return (it.object().status.phase == "Complete")
//                     }
//                 }
//             }
//         }
//     }
//     stage('Test') {
//         echo "Test"
//         sleep 2
//     }
// }
// node ('maven') {
//     stage('DeployDev') {
//         echo "Deploy to $dev_project"
//         openshift.withCluster() {
//             openshift.withProject() {
//                 // Tag the latest image to be used in dev stage
//                 openshift.tag("$project/$application:latest", "$project/$application:$dev_project")
//             }
//             openshift.withProject(dev_project) {
//                 // trigger Deployment in dev project
//                 def dc = openshift.selector('dc', "$application")
//                 dc.rollout().status()
//             }
//         }
//     }
//     stage('PromoteTest') {
//         echo "Deploy to $test_project"
//         openshift.withCluster() {
//             openshift.withProject() {
//                 // Tag the dev image to be used in test stage
//                 openshift.tag("$project/$application:$dev_project", "$project/$application:$test_project")
//             }
//             openshift.withProject(test_project) {
//                 // trigger Deployment in test project
//                 def dc = openshift.selector('dc', "$application")
//                 dc.rollout().status()
//             }
//         }
//     }
//     stage('PromoteProd') {
//         echo "Deploy to $prod_project"
//         openshift.withCluster() {
//             openshift.withProject() {
//                 // Tag the test image to be used in prod stage
//                 openshift.tag("$project/$application:$test_project", "$project/$application:$prod_project")
//             }
//             openshift.withProject(prod_project) {
//                 // trigger Deployment in prod project
//                 def dc = openshift.selector('dc', "$application")
//                 dc.rollout().status()
//             }
//         }
//     }
// }
//
// //  oc new-app fabric8/s2i-java:latest-java11~https://github.com/marzelwidmer/catalog-service.git; oc expose svc/catalog-service; oc get route catalog-service
//
//
// // def project="catalog-service"
// //
// // node('maven') {
// //   stage 'build & deploy in dev'
// //   openshiftBuild(namespace: 'development',
// //   			    buildConfig: ${project},
// // 			    showBuildLogs: 'true',
// // 			    waitTime: '3000000')
// //
// //   stage 'verify deploy in dev'
// //   openshiftVerifyDeployment(namespace: 'development',
// // 				       depCfg: ${project},
// // 				       replicaCount:'1',
// // 				       verifyReplicaCount: 'true',
// // 				       waitTime: '300000')
// //
// //   stage 'deploy in test'
// //   openshiftTag(namespace: 'development',
// //   			  sourceStream: ${project},
// // 			  sourceTag: 'latest',
// // 			  destinationStream: ${project},
// // 			  destinationTag: 'promoteQA')
// //
// //   openshiftDeploy(namespace: 'testing',
// //   			     deploymentConfig: ${project},
// // 			     waitTime: '300000')
// //
// //   openshiftScale(namespace: 'testing',
// //   			     deploymentConfig: ${project},
// // 			     waitTime: '300000',
// // 			     replicaCount: '2')
// //
// //   stage 'verify deploy in test'
// //   openshiftVerifyDeployment(namespace: 'testing',
// // 				       depCfg: ${project},
// // 				       replicaCount:'2',
// // 				       verifyReplicaCount: 'true',
// // 				       waitTime: '300000')
// //
// //   stage 'Deploy to production'
// //   timeout(time: 2, unit: 'DAYS') {
// //       input message: 'Approve to production?'
// //  }
// //
// //   openshiftTag(namespace: 'development',
// //   			  sourceStream: ${project},
// // 			  sourceTag: 'promoteQA',
// // 			  destinationStream: ${project},
// // 			  destinationTag: 'promotePRD')
// //
// //
// //   openshiftDeploy(namespace: 'production',
// //   			     deploymentConfig: ${project},
// // 			     waitTime: '300000')
// //
// //   openshiftScale(namespace: 'production',
// //   			     deploymentConfig: ${project},
// // 			     waitTime: '300000',
// // 			     replicaCount: '2')
// //
// //   stage 'verify deploy in production'
// //   openshiftVerifyDeployment(namespace: 'production',
// // 				       depCfg: ${project},
// // 				       replicaCount:'2',
// // 				       verifyReplicaCount: 'true',
// // 				       waitTime: '300000')
// // }