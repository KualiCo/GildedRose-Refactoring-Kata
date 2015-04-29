package com.gildedrose;

class GildedRose {
    static final int MAX_QUALITY = 50;
	static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
	static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
	static final String AGED_BRIE = "Aged Brie";
	static final String CONJURED_ITEM_PREFIX = "Conjured ";
	
	Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item currentItem = items[i];
            handleSellInValue(currentItem);
            if (currentItem.name.equals(AGED_BRIE)) {
            	handleAgedBrie(currentItem);
            } else if (currentItem.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)) {
            	handleBackstagePasses(currentItem);
            } else if (isHandOfRagnaros(currentItem)) {
            	handleSulfuras(currentItem);
        	} else if (currentItem.name.startsWith(CONJURED_ITEM_PREFIX)) {
        		handleConjuredItem(currentItem);
        	} else {
				handleItem(currentItem);
	        }
            handleQualityMinMax(currentItem);
        }   
    }

	private void handleQualityMinMax(Item currentItem) {
		if (!isHandOfRagnaros(currentItem)) {
			if (currentItem.quality > MAX_QUALITY) {
				currentItem.quality = MAX_QUALITY;
			} else if (currentItem.quality < 0) {
				currentItem.quality = 0;
			}
		}
	}
    
	private void handleSellInValue(Item currentItem) {
        if (!isHandOfRagnaros(currentItem)) {
        	currentItem.sellIn = currentItem.sellIn - 1;
        }
	}
    
    private void handleSulfuras(Item hand) {
    	//the hand is immortal and unchanging
    }

	private void handleItem(Item currentItem) {
		currentItem.quality = currentItem.quality - 1;
		if (currentItem.sellIn < 0) {
            currentItem.quality = currentItem.quality - 1;
		}
	}
    
    private void handleAgedBrie(Item brie) {
    	brie.quality += 1;
    	if (brie.sellIn < 0) {
    		brie.quality += 1;
    	}
    }

	private void handleBackstagePasses(Item currentItem) {
		if (currentItem.sellIn < 0) {
			currentItem.quality = 0;
		} else {
			currentItem.quality += 1; 
			if (currentItem.sellIn < 11) {
			    if (currentItem.quality < MAX_QUALITY) {
			        currentItem.quality = currentItem.quality + 1;
			    }
			}
	
			if (currentItem.sellIn < 6) {
			    if (currentItem.quality < MAX_QUALITY) {
			        currentItem.quality = currentItem.quality + 1;
			    }
			}
		}
	}
	
	private void handleConjuredItem(Item item) {
		item.quality -= 2;
		if (item.sellIn < 0) {
			item.quality -= 2;
		}
	}
	
	private boolean isHandOfRagnaros(Item currentItem) {
		return currentItem.name.equals(SULFURAS_HAND_OF_RAGNAROS);
	}
}
