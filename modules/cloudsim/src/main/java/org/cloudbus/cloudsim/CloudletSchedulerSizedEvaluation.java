package org.cloudbus.cloudsim;

import java.util.List;

public class CloudletSchedulerSizedEvaluation extends CloudletScheduler {
	protected int currentCpus;

	public CloudletSchedulerSizedEvaluation() {
		super();
		currentCpus = 0;
	}

	@Override
	public double updateVmProcessing(double currentTime, List<Double> mipsShare) {
		return 0;
	}

	@Override
	public Cloudlet cloudletCancel(int cloudletId) {
		Cloudlet tmpCloudlet = null;

		return tmpCloudlet;
	}

	@Override
	public boolean cloudletPause(int cloudletId) {
		return false;
	}

	@Override
	public void cloudletFinish(ResCloudlet rcl) { }

	@Override
	public double cloudletResume(int cloudletId) {
		return 0;
	}

	@Override
	public double cloudletSubmit(Cloudlet cloudlet, double fileTransferTime) {
		return 0;
	}

	@Override
	public double cloudletSubmit(Cloudlet cloudlet) {
		return 0;
	}

	@Override
	public int getCloudletStatus(int cloudletId) {
		return 0;
	}

	@Override
	public double getTotalUtilizationOfCpu(double time) {
		return 0;
	}

	@Override
	public boolean isFinishedCloudlets() {
		return false;
	}

	@Override
	public Cloudlet getNextFinishedCloudlet() {
		Cloudlet tmpCloudlet = null;

		return tmpCloudlet;
	}

	@Override
	public int runningCloudlets() {
		return 0;
	}

	@Override
	public Cloudlet migrateCloudlet() {
		Cloudlet tmpCloudlet = null;

		return tmpCloudlet;
	}

	@Override
	public List<Double> getCurrentRequestedMips() {
		List<Double> tmpList = null;

		return tmpList;
	}

	@Override
	public double getTotalCurrentAvailableMipsForCloudlet(ResCloudlet rcl, List<Double> mipsShare) {
		return 0;
	}

	@Override
	public double getTotalCurrentAllocatedMipsForCloudlet(ResCloudlet rcl, double time) {
		return 0;
	}

	@Override
	public double getTotalCurrentRequestedMipsForCloudlet(ResCloudlet rcl, double time) {
		return 0;
	}

	@Override
	public double getCurrentRequestedUtilizationOfRam() {
		return 0;
	}

	@Override
	public double getCurrentRequestedUtilizationOfBw() {
		return 0;
	}
}