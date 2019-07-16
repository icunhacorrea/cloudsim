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
		setWorkloadPerVm();
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

	protected Map<Integer, Double> getWorkloadPerVm() {
		return workloadPerVm;
	}

	protected void setWorkloadPerVm() {
		workloadPerVm = new HashMap<Integer, Double>();
		double valuePerVm = 0;
		long totalVmMips = 0;
		for (Vm vm : vmList)
			totalVmMips += vm.getMips();
		for (Vm vm : vmList) {
			valuePerVm = vm.getMips() / (double) totalVmMips;
			workloadPerVm.put(vm.getId(), valuePerVm);
		}
	}

	/**
	 * Método da classe responsável por avaliar se uma determinada Vm está
	 * apta a roubas Cloudlets de alguma outra máquina VM.
	 * 
	 * @param vmId Identificação Vm que acabou de terminar a execução de uma Cl
	 * @param processedByVms Quantidade de mips já processada pela Vm.
	 * 
	 */
	protected boolean verifyLoadVm(int vmId, long processedByVm) {
		double workLoadOfVm = workloadPerVm.get(vmId) * (double) totalCLsLength;
		// Se a Vm já processou 3/4 do que deve processar OU sua lista de cloudlets em
		// espera está vazia.
		if (processedByVm > (0.75 * workLoadOfVm) || 
			vmList.get(vmId).getCloudletScheduler().getCloudletWaitingList().size() == 0) {
			return true;
		}
		return false;
	}


	/**
	 * To do: Alterar método para que ele faça toda a preparação de um stealPack.
	 * 
	 * @param vmDestId
	 * @return
	 */
	protected int[] findVmToSteal(int vmDestId) {
		int arr[] = {-1, -1};
		double mipsDestVm = vmList.get(vmDestId).getMips();
		List <ResCloudlet> waitingList;
		for (Vm vm : vmList) {
			if (vm.getCloudletScheduler().getCloudletWaitingList().size() > 0 &&
				vm.getMips() < mipsDestVm) {
				waitingList = vm.getCloudletScheduler().getCloudletWaitingList();
				arr[0] = vm.getId();      // VM emissora
				arr[1] = waitingList.get(0).getCloudletId();
			}
		}
		return arr;
	}

	/**
	 * Método repsonsável por identificar uma Vm emissora de Cloudlets, bem como
	 * uma Cloudlet na sua lista de espera.
	 * 
	 * @param vmDestId VM destino para a carga de trabalho
	 * @param brokerId User responsável pela troca.
	 * @param dcId     Id do datacenter onde que receberá a nova carga de trabalho
	 * @param 
	 */
	protected int[] prepareStealPack(int vmDestId, int brokerId, int dcId) {
		int arr[] = {-1, -1, -1, -1, -1};
		double mipsDestVm = vmList.get(vmDestId).getMips();
		List <ResCloudlet> waitingList;
		for (Vm vm : vmList) {
			if (vm.getCloudletScheduler().getCloudletWaitingList().size() > 0 &&
				vm.getMips() < mipsDestVm) {
				waitingList = vm.getCloudletScheduler().getCloudletWaitingList();
				arr[2] = vm.getId();      // VM emissora
				arr[0] = waitingList.get(0).getCloudletId();
			}
		}
		arr[1] = brokerId;
		arr[3] = vmDestId;
		arr[4] = dcId;
		return arr;
	}
}