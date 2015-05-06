type Item {
  name: string;
  sell_in: number;
  quality: number;
}

function Item(name, sell_in, quality) {
  this.name = name;
  this.sell_in = sell_in;
  this.quality = quality;
}

function extractByName(name:string, items:Array<Item>):Array<Item> {
  // ...
}

function withoutByName(name:string, items:Array<Item>):Array<Item> {
  // ...
}

function updateBackstagePasses(items:Array<Item>):Array<Item> {
  // ...
}

function updateLegendaryItems(items:Array<Item>):Array<Item> {
  // ...
}

function updateAgedBrieItems(items:Array<Item>):Array<Item> {
  // ...
}

function updateConjuredItems(items:Array<Item>):Array<Item> {
  // ...
}

function updateGeneralItems(items:Array<Item>):Array<Item> {
  // ...
}

function updateQuality(items:Array<Item>):Array<Item> {
  var backstagePasses = extractByName('Backstage passes to a TAFKAL80ETC concert', items)
  backstagePasses = updateBackstagePasses(backstagePasses)
  items = withoutByName('Backstage passes to a TAFKAL80ETC concert', items)

  var brie = extractByName('Aged Brie', items)
  brie = updateAgedBrieItems(brie)
  items = withoutByName('Aged Brie')

  // ... do the other special ones, blah blah

  items = updateGeneralItems(items)

  return concat([], backstagePasses, brie, items)
}
