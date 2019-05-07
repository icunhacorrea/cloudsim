package org.cloudbus.cloudsim;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CapacityLoadBalancer {

	protected List<? extends Cloudlet> cloudletList;

	protected List<? extends Vm> vmList;

	protected long totalCLsLength;

	protected Map<Integer, Double> workloadPerVm;


	public CapacityLoadBalancer(List<? extends Cloudlet> cloudletList,
		List<? extends Vm> vmList) {
		setVmList(vmList);
		setCloudletList(cloudletList);
		setTotalCLsLength();
		this.workloadPerVm = findVmProcessLimit();
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

	protected long getTotalLengthOfCloudlets() {
		return totalCLsLength;
	}

	protected void setTotalCLsLength() {
		for (Cloudlet cl : cloudletList)
			this.totalCLsLength += cl.getCloudletTotalLength();
	}

	protected long getTotalCLsLength() {
		return totalCLsLength;
	}

	protected Map<Integer, Double> findVmProcessLimit() {
		Map<Integer, Double> workloadPerVm = new HashMap<Integer, Double>();
		double valuePerVm = 0;
		long totalVmMips = 0;
		for (Vm vm : vmList)
			totalVmMips += vm.getMips();
		for (Vm vm : vmList) {
			valuePerVm = vm.getMips() / (double) totalVmMips;
			workloadPerVm.put(vm.getId(), valuePerVm);
		}
		return workloadPerVm;
	}

	protected boolean verifyLoadVm(long vmAtualLoad) {
		return false;
	}

}