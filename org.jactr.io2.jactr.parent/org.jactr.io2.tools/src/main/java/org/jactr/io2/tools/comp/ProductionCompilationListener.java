package org.jactr.io2.tools.comp;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.jactr.core.concurrent.ExecutorServices;
import org.jactr.core.model.IModel;
import org.jactr.core.module.procedural.six.learning.IProceduralLearningModule6;
import org.jactr.core.module.procedural.six.learning.event.IProceduralLearningModule6Listener;
import org.jactr.core.module.procedural.six.learning.event.ProceduralLearningEvent;
import org.jactr.core.production.IProduction;
import org.jactr.core.runtime.ACTRRuntime;
import org.jactr.instrument.IInstrument;
import org.jactr.io2.ast.ASTGeneratorManager;
import org.jactr.io2.ast.IASTGenerator;
import org.jactr.io2.source.ISourceGenerator;
import org.jactr.io2.source.SourceGeneratorManager;

public class ProductionCompilationListener implements IInstrument {

	private IASTGenerator _astGenerator;
	private ISourceGenerator _sourceGenerator;
	private String _format = "jactr";
	private Map<IModel, PrintWriter> _streams = new HashMap<>();

	private IProceduralLearningModule6Listener _listener = new IProceduralLearningModule6Listener() {
		@Override
		public void rewarded(ProceduralLearningEvent event) {
		}

		@Override
		public void startReward(ProceduralLearningEvent event) {
		}

		@Override
		public void stopReward(ProceduralLearningEvent event) {
		}

		@Override
		public void productionCompiled(ProceduralLearningEvent event) {
			log(event.getSimulationTime(), event.getSource().getModel(), event.getParents()[0], event.getParents()[1], event.getProduction());
		}
		
		public void productionNotCompiled(ProceduralLearningEvent event)
		{
			log(event.getSimulationTime(), event.getSource().getModel(), event.getParents()[0], event.getParents()[1], event.getMessage());
		}
	};

	@Override
	public void install(IModel model) {
		if (attachListener(model))
			openStream(model);
	}

	private boolean attachListener(IModel model) {
		IProceduralLearningModule6 module = (IProceduralLearningModule6) model
				.getModule(IProceduralLearningModule6.class);
		if (module == null)
			return false;

		module.addListener(_listener, ExecutorServices.getExecutor(ExecutorServices.BACKGROUND));

		return true;
	}

	private void openStream(IModel model) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(
					new File(ACTRRuntime.getRuntime().getWorkingDirectory(), model.getName() + "-compilation.xml")));
			_streams.put(model, pw);
			pw.println("<compilations>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void uninstall(IModel model) {
		if (detachListener(model))
			closeStream(model);
	}

	private void closeStream(IModel model) {
		try {
			PrintWriter pw = _streams.remove(model);

			if (pw != null) {
				pw.println("</compilations>");
				pw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean detachListener(IModel model) {
		IProceduralLearningModule6 module = (IProceduralLearningModule6) model
				.getModule(IProceduralLearningModule6.class);
		if (module == null)
			return false;

		module.removeListener(_listener);
		return true;
	}

	@Override
	public void initialize() {

		_astGenerator = ASTGeneratorManager.get().getASTGenerator(_format).get();
		_sourceGenerator = SourceGeneratorManager.get().getSourceGenerator(null, _format).get();

	}

	protected void log(double when, IModel model, IProduction parentA, IProduction parentB, IProduction child) {
		PrintWriter pw = _streams.get(model);
		pw.format(" <compilation time=\"%.2f\">\n", when);
		pw.println("  <parent-a><![CDATA[");
		pw.println(_sourceGenerator.generate(_astGenerator.generate(parentA, _format), _format));
		pw.println("  ]]></parent-a>");
		pw.println("  <parent-b><![CDATA[");
		pw.println(_sourceGenerator.generate(_astGenerator.generate(parentB, _format), _format));
		pw.println("  ]]></parent-b>");
		pw.println("  <child><![CDATA[");
		pw.println(_sourceGenerator.generate(_astGenerator.generate(child, _format), _format));
		pw.println("  ]]></child>");
		pw.print(" </compilation>\n");
		pw.flush();
	}
	
	protected void log(double when, IModel model, IProduction parentA, IProduction parentB, String message) {
		PrintWriter pw = _streams.get(model);
		pw.format(" <compilation time=\"%.2f\">\n", when);
		pw.println("  <parent-a><![CDATA[");
		pw.println(_sourceGenerator.generate(_astGenerator.generate(parentA, _format), _format));
		pw.println("  ]]></parent-a>");
		pw.println("  <parent-b><![CDATA[");
		pw.println(_sourceGenerator.generate(_astGenerator.generate(parentB, _format), _format));
		pw.println("  ]]></parent-b>");
		pw.println("  <message>");
		pw.println(message);
		pw.println("  </message>");
		pw.print(" </compilation>\n");
		pw.flush();
	}

}
