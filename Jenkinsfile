		pipeline{
			agent any
			stages{
				stage("Build"){
					steps{
						echo("Build the project")
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
					    echo("Running regression tests")
					}
				}
				stage("Deploy to Stg"){
					steps{
						echo("Deploying to Stg env")
					}
				}
			    stage("Run sanity tests"){
				    steps{
					    echo("Running sanity tests")
					}
				}
			    stage("Deploy to Prod"){
				    steps{
					    echo("Deploying to prod")
					}
				}			
			}
		}