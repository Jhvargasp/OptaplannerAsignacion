package principal;

import org.optaplanner.core.api.solver.event.BestSolutionChangedEvent;
import org.optaplanner.core.api.solver.event.SolverEventListener;

public class Listener implements SolverEventListener {

	@Override
	public void bestSolutionChanged(BestSolutionChangedEvent arg0) {
		System.out.println("Mejor marcador: "+arg0.getNewBestSolution().getScore());

	}

}
