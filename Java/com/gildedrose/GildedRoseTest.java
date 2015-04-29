package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    private static final String FOOBAR = "foobar";

	@Test
    public void foo() {
        Item[] items = new Item[] { new Item(FOOBAR, 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(FOOBAR, app.items[0].name);
    }
	
	@Test
	public void testItemOrder() {
        Item[] items = new Item[] { new Item(FOOBAR, 0, 0), new Item(GildedRose.AGED_BRIE, 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(FOOBAR, app.items[0].name);
        assertEquals(GildedRose.AGED_BRIE, app.items[1].name);
	}
    
    @Test
    public void testSulfuras() {
    	Item[] items = new Item[] { new Item(GildedRose.SULFURAS_HAND_OF_RAGNAROS, 20, 80) };
    	GildedRose app = new GildedRose(items);
    	app.updateQuality();
    	assertEquals(80, app.items[0].quality);
    	assertEquals(20, app.items[0].sellIn);
    }
    
    @Test
    public void testTypicalItem() {
    	int origSellIn = 10;
    	int origQuality = 10;
    	Item[] items = new Item[] { new Item(FOOBAR, origSellIn, origQuality) };
    	GildedRose app = new GildedRose(items);
    	app.updateQuality();
    	assertEquals(origSellIn-1, app.items[0].sellIn);
    	assertEquals(origQuality-1, app.items[0].quality);
    }
    
    @Test
    public void testConjuredItem() {
    	int origSellIn = 10;
    	int origQuality = 10;
    	Item[] items = new Item[] { new Item(GildedRose.CONJURED_ITEM_PREFIX + FOOBAR, origSellIn, origQuality) };
    	GildedRose app = new GildedRose(items);
    	app.updateQuality();
    	assertEquals(origSellIn-1, app.items[0].sellIn);
    	assertEquals(origQuality-2, app.items[0].quality);
    }
    
    @Test
    public void testExpiredItem() {
    	int origSellIn = 0;
    	int origQuality = 10;
    	Item[] items = new Item[] { new Item(FOOBAR, origSellIn, origQuality) };
    	GildedRose app = new GildedRose(items);
    	app.updateQuality();
    	assertEquals(origSellIn-1, app.items[0].sellIn);
    	assertEquals(origQuality-2, app.items[0].quality);
    }
    
    @Test
    public void testExpiredConjuredItem() {
    	int origSellIn = 0;
    	int origQuality = 10;
    	Item[] items = new Item[] { new Item(GildedRose.CONJURED_ITEM_PREFIX + FOOBAR, origSellIn, origQuality) };
    	GildedRose app = new GildedRose(items);
    	app.updateQuality();
    	assertEquals(origSellIn-1, app.items[0].sellIn);
    	assertEquals(origQuality-4, app.items[0].quality);
    }
    
    @Test
    public void testZeroQualityItem() {
    	int origSellIn = -2;
    	int origQuality = 0;
    	Item[] items = new Item[] { new Item(FOOBAR, origSellIn, origQuality) };
    	GildedRose app = new GildedRose(items);
    	app.updateQuality();
    	assertEquals(origSellIn-1, app.items[0].sellIn);
    	assertEquals(origQuality, app.items[0].quality);
    }
    
    @Test
    public void testAgedBrie() {
    	int origSellIn = 10;
    	int origQuality = 10;
    	Item[] items = new Item[] { new Item(GildedRose.AGED_BRIE, origSellIn, origQuality) };
    	GildedRose app = new GildedRose(items);
    	app.updateQuality();
    	assertEquals(origSellIn-1, app.items[0].sellIn);
    	assertEquals(origQuality+1, app.items[0].quality);
    }
    
    @Test
    public void testAgedBrieMax() {
    	int origSellIn = 10;
    	int origQuality = GildedRose.MAX_QUALITY;
    	Item[] items = new Item[] { new Item(GildedRose.AGED_BRIE, origSellIn, origQuality) };
    	GildedRose app = new GildedRose(items);
    	app.updateQuality();
    	assertEquals(origSellIn-1, app.items[0].sellIn);
    	assertEquals(origQuality, app.items[0].quality);    	
    }
    
    @Test
    public void testExpiredAgedBrie_Mmmmm() {
    	int origSellIn = 0;
    	int origQuality = 5;
    	Item[] items = new Item[] { new Item(GildedRose.AGED_BRIE, origSellIn, origQuality) };
    	GildedRose app = new GildedRose(items);
    	app.updateQuality();
    	assertEquals(origSellIn-1, app.items[0].sellIn);
    	assertEquals(origQuality+2, app.items[0].quality);    	
    }
    
    @Test
    public void testFarAwayTickets() {
    	int origSellIn = 20;
    	int origQuality = 10;
    	Item[] items = new Item[] { new Item(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, origSellIn, origQuality) };
    	GildedRose app = new GildedRose(items);
    	app.updateQuality();
    	assertEquals(origSellIn-1, app.items[0].sellIn);
    	assertEquals(origQuality+1, app.items[0].quality);
    }

    @Test
    public void testCloseTickets() {
    	int origSellIn = 10;
    	int origQuality = 10;
    	Item[] items = new Item[] { new Item(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, origSellIn, origQuality) };
    	GildedRose app = new GildedRose(items);
    	app.updateQuality();
    	assertEquals(origSellIn-1, app.items[0].sellIn);
    	assertEquals(origQuality+2, app.items[0].quality);
    }

    @Test
    public void testReallyCloseTickets() {
    	int origSellIn = 5;
    	int origQuality = 10;
    	Item[] items = new Item[] { new Item(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, origSellIn, origQuality) };
    	GildedRose app = new GildedRose(items);
    	app.updateQuality();
    	assertEquals(origSellIn-1, app.items[0].sellIn);
    	assertEquals(origQuality+3, app.items[0].quality);
    }
    
    @Test
    public void testMaxTickets() {
    	int origSellIn = 5;
    	int origQuality = GildedRose.MAX_QUALITY;
    	Item[] items = new Item[] { new Item(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, origSellIn, origQuality) };
    	GildedRose app = new GildedRose(items);
    	app.updateQuality();
    	assertEquals(origSellIn-1, app.items[0].sellIn);
    	assertEquals(GildedRose.MAX_QUALITY, app.items[0].quality);
    }
        
    @Test
    public void testYesterdaysTickets() {
    	int origSellIn = 0;
    	int origQuality = 10;
    	Item[] items = new Item[] { new Item(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, origSellIn, origQuality) };
    	GildedRose app = new GildedRose(items);
    	app.updateQuality();
    	assertEquals(origSellIn-1, app.items[0].sellIn);
    	assertEquals(0, app.items[0].quality);
    }
    
}
