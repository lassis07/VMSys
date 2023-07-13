package br.ufc.tpii.vmsys;

import br.ufc.tpii.vmsys.exceptions.InsufficientFunds;
import br.ufc.tpii.vmsys.exceptions.InvalidSelection;
import br.ufc.tpii.vmsys.exceptions.OutOfStock;
import br.ufc.tpii.vmsys.inventory.Inventory;
import br.ufc.tpii.vmsys.inventory.Item;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemNotFound;

public class VendingMachine {

	private double coinsDeposited = 0.0;

	private Inventory inventory;

	public VendingMachine(Inventory inventory) {
		this.inventory = inventory;
	}

	public void addCoins(double coins) {
    if (coins < 0) {throw new NegativeFundsException("Não é possível adicionar um valor negativo de moedas");}
		this.coinsDeposited += coins;
	}
	
	public double withdrawRemainingCoins() {
		double remainingCoins = this.coinsDeposited;
		this.coinsDeposited = 0.0;
		return remainingCoins;
	}

	public double howManyCoinsLeft() {
		return this.coinsDeposited;
	}

	public void vend(String itemName) throws InvalidSelection, OutOfStock, InsufficientFunds {
		Item item = null;

		try {
			item = inventory.getItem(itemName);
			
		} catch (ItemNotFound inf) {
			throw new InvalidSelection("Seleção de item inválido: " + itemName);
		}

		if (item.getCount() <= 0) {
			throw new OutOfStock("Item fora do estoque: " + itemName);
		}

		if (item.getPrice() > this.coinsDeposited) {
			throw new InsufficientFunds("Moedas depositadas insuficientes para comprar " + itemName + ": " + this.coinsDeposited);
		}

		this.coinsDeposited -= item.getPrice();

		item.decCount();
	}
}