package ch.ethz.matsim.baseline_scenario.utils;

import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.population.Activity;
import org.matsim.facilities.ActivityFacilityImpl;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.api.core.v01.population.Population;
import org.matsim.core.network.NetworkUtils;
import org.matsim.facilities.ActivityFacilities;
import org.matsim.facilities.ActivityFacility;
import org.matsim.facilities.Facility;

public class FixLinkIds {
	final private Network network;
	
	public FixLinkIds(Network network) {
		this.network = network;
	}
	
	public void run(ActivityFacilities facilities, Population population) {
		for (ActivityFacility facility : facilities.getFacilities().values()) {
			((ActivityFacilityImpl) facility).setLinkId(NetworkUtils.getNearestLink(network, facility.getCoord()).getId());
		}
		
		for (Person person : population.getPersons().values()) {
			for (Plan plan : person.getPlans()) {
				for (PlanElement element : plan.getPlanElements()) {
					if (element instanceof Activity) {
						Activity activity = (Activity) element;
						
						if (activity.getFacilityId() != null) {
							activity.setLinkId(facilities.getFacilities().get(activity.getFacilityId()).getLinkId());
						}
					}
				}
			}
		}
	}
}
