process Milestone2 {
	sequence SourceCodeUpdate {
		action {
			type { executable }
			agent { PEOS User }
			tool { CVS }
			input { RepositoryCheckOut.pml }
			requires { RepositoryCheckOut.pml }
			provides { current source code }
		}

		branch DeliverableCreation {
			action MilestoneOperationalConcept {
				type { executable }
				agent { PEOS User }
				tool { pml compiler }
				input { OperationalConcept.pml }
				requires { OperationalConcept.pml }
				providee { operational concept }
			}
			action MilestoneAnalysis {
				type { executable }
				agent { PEOS User }
				tool { pml compiler }
				input { Analysis.pml }
				requires { Analysis.pml }
				providee { Analysis }
			}
			action MilestoneRiskIdentification {
				type { executable }
				agent { PEOS User }
				tool { pml compiler }
				input { RiskIdentification.pml }
				requires { RiskIdentification.pml }
				providee { RiskIdentification }
			}
			action MilestoneLog {
				type { executable }
				agent { PEOS User }
				tool { pml compiler }
				input { Log.pml }
				requires { Log.pml }
				providee { Log }
			}
		}
		sequence RepositoryUpdate {
			action MilestoneRespositorySynchronize {
				type { executable }
				agent { PEOS User }
				tool { PML Compiler }
				input { RepositorySynchronize.pml }
				requires { RepositorySynchronize.pml }
				provides { Synchronized Source Code }
			}
			action MilestoneRespositoryCheckIn {
				type { executable }
				agent { PEOS User }
				tool { PML Compiler }
				input { RepositoryCheckIn.pml }
				requires { RepositoryCheckIn.pml }
				provides { Updated Repository } 
			}
		}
	}
}
				
