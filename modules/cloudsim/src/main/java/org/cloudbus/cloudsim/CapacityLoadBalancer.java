package org.cloudbus.cloudsim;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cloudbus.cloudsim.lists.CloudletList;
import org.cloudbus.cloudsim.lists.VmList;

public class CapacityLoadBalancer {

	private long cloudletsTotalLength;

	private Map<Integer, Long> loadToVm;

	protected List<? extends Cloudlet> cloudletList;

	protected List<? extends Vm> vmList;


	public CapacityLoadBalancer(List<? extends Cloudlet> cloudletList,
		List<? extends Vm> vmList) throws Exception {
		setVmList(vmList);
		setCloudletList(cloudletList);
		setLoadToVm(new HashMap<Integer, Long>());
	}

	@SuppressWarnings("unchecked")
	public <T extends Vm> List<T> getVmList() {
		return (List<T>) vmList;
	}

	protected <T extends Vm> void setVmList(List<T> vmList) {
		this.vmList = vmList;
	}

	@SuppressWarnings("unchecked")
	public <T extends Cloudlet> List<T> getCloudletList() {
		return (List<T>) cloudletList;
	}

	protected <T extends Cloudlet> void setCloudletList(List<T> cloudletList) {
		this.cloudletList = cloudletList;
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, Long> getLoadToVm () {
		return loadToVm;
	}

	protected void setLoadToVm(Map<Integer, Long> loadToVm) {
		this.loadToVm = loadToVm;
	}

	protected long getTotalLengthOfCloudlets() {
		long totalLength = 0;

		for (Cloudlet cl : cloudletList) {
			totalLength += cl.getCloudletTotalLength();

		}
		return totalLength;
	}

	protected Map<Integer, Long> findVmProcessLimit() {
		long 
		for (Vm vm : vmList) {

		}
	}

}