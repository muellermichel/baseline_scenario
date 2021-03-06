package ch.ethz.matsim.baseline_scenario.analysis.counts.run;

import java.io.IOException;
import java.util.Collection;

import ch.ethz.matsim.baseline_scenario.analysis.counts.items.HourlyCountItem;
import ch.ethz.matsim.baseline_scenario.analysis.counts.readers.CSVHourlyCountsReader;
import ch.ethz.matsim.baseline_scenario.analysis.counts.readers.HourlySimulationCountsListener;
import ch.ethz.matsim.baseline_scenario.analysis.counts.readers.SimulationCountsReader;
import ch.ethz.matsim.baseline_scenario.analysis.counts.writers.CSVHourlyCountsWriter;

public class ComputeHourlyCounts {
	static public void main(String[] args) throws IOException {
		Collection<HourlyCountItem> items = new CSVHourlyCountsReader().read(args[0]);
		new SimulationCountsReader(new HourlySimulationCountsListener(items)).read(args[1]);
		new CSVHourlyCountsWriter(items).write(args[2]);
	}
}
