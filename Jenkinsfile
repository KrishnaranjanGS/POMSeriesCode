		pipeline{
			agent any

			tools{
				maven 'maven'
			}

			stages{
				stage("Build"){
					steps{
						git 'https://github.com/jglick/simple-maven-project-with-tests'
						sh "mvn -Dmaven.test.failure.ignore=true clean package"
					}
					post
					{
						success
						{
							junit '**/target/surefire-reports/TEST-*.xml'
							archiveArtifacts 'target/*.jar'
						}
					}
				}

				stage("Deploy to Dev"){
					steps{
						echo("Deploying to Dev env")
					}
				}
				
			    stage("Deploy to QA"){
				    steps{
					    echo("Deploying to QA env")
					}
				}
				
			    stage("Run automated regression test cases"){
				    steps{
					    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE'){
							git'https://github.com/KrishnaranjanGS/POMSeriesCode'
							sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testRunners/testNG_regression.xml"
						}
					}
				}
				
				stage("Publish regression Allure reports"){
					steps{
						script{
							allure([
								includeProperties: false,
								jdk: '',
								properties: [],
								reportBuildPolicy: 'ALWAYS',
								results: [[path: '/allure-results']]
							])
						}
					}
				}
				
				stage("Publish regression Extent reports"){
					steps{
						publishHTML([
							allowMissing: false,
							alwaysLinkToLastBuild: false,
							keepAll: true,
							reportDir: 'reports',
							reportFiles: 'TestExecutionReport.html',
							reportName: 'HTML regression Extent Report',
							reportTitles: ''
						])
					}
				}
				
				
				stage("Deploy to Stg"){
					steps{
						echo("Deploying to Stg env")
					}
				}
				
			    stage("Run smoke tests"){
				    steps{
					    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE'){
						git 'https://github.com/KrishnaranjanGS/POMSeriesCode'
						sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testRunners/testNG_smoke.xml"
					}
				}
				
			    stage("Publish Smoke Extent reports"){
					steps{
						publishHTML([
							allowMissing: false,
							alwaysLinkToLastBuild: false,
							keepAll: true,
							reportDir: 'reports',
							reportFiles: 'TestExecutionReport.html',
							reportName: 'HTML Smoke Extent Report',
							reportTitles: ''
						])
					}
				}			
			}
		}