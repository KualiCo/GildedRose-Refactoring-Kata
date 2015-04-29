function Item(name, sell_in, quality) {
  this.name = name;
  this.sell_in = sell_in;
  this.quality = quality;
}

var items = []

const MAX_QUALITY = 50

function update_quality() {
  items.forEach(item => {
    updateItemSellBy(item)
    updateItemQuality(item)
  })
}

function updateItemSellBy(item) {
  var itemSellByAdjustor = ItemSellByAdjustors[item.name] || ItemSellByAdjustors.default
  var sellByAdjustmentAmount = itemSellByAdjustor(item)
  item.sellBy = item.sellBy + sellByAdjustmentAmount
}

function updateItemQuality(item) {
  if (item.quality > 0 && item.quality < MAX_QUALITY) {
    var itemQualityAdjustor = ItemQualityAdjustors[item.name] || ItemQualityAdjustors.default
    var qualityAdjustmentAmount = itemQualityAdjustor(item)
    var updatedQuality = item.quality + qualityAdjustmentAmount
    setItemQuality(item, updatedQuality)
  }
}

function setItemQuality(item, updatedQuality) {
  if (updatedQuality < 0) updatedQuality = 0
  else if (updatedQuality > MAX_QUALITY) updatedQuality = MAX_QUALITY
  item.quality = updatedQuality
}

const ItemSellByAdjustors = {
  'Sulfuras, Hand of Ragnaros': item => 0,
   default: item => -1
}

const ItemQualityAdjustors = {
  'Sulfuras, Hand of Ragnaros': item => 0,
  'Aged Brie': item => 1,
  'Backstage passes to a TAFKAL80ETC concert': item => {
    var adjustment = 1
    if (item.sellBy < 0) adjustment = -item.quality
    else if (item.sellBy < 6) adjustment = 3
    else if (item.sellBy < 11) adjustment = 2
    return adjustment
  },
  'Conjured Mana Cake': item => {
     var pastSellByDate = item.sellBy < 0
     return pastSellByDate ? -4 : -2
  },
  default: item => {
     var pastSellByDate = item.sellBy < 0
     return pastSellByDate ? -2 : -1
  }
}
