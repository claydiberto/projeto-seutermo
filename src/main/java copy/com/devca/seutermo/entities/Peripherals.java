package com.devca.seutermo.entities;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.devca.seutermo.entities.enums.PeripheralsType;

public class Peripherals {
	
	@Enumerated()
	private PeripheralsType peripheralsType;

	public Peripherals() {
	}

	public Peripherals(PeripheralsType peripheralsType) {
		this.peripheralsType = peripheralsType;
	}

	public PeripheralsType getPeripheralsType() {
		return peripheralsType;
	}

	public void setPeripheralsType(PeripheralsType peripheralsType) {
		this.peripheralsType = peripheralsType;
	}

	@Override
	public String toString() {
		return "Peripherals [peripheralsType=" + peripheralsType + "]";
	}

}
