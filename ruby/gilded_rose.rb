class GildedRose

  def initialize items
    @items = items
  end

  def update_quality
    @items.each do |item|
      case item.name
      when "Aged Brie"
        AgedBrie.new(item).update
      when "Sulfuras, Hand of Ragnaros"
        Sulfuras.new(item).update
      when "Backstage passes to a TAFKAL80ETC concert"
        Backstage.new(item).update
      else
        Default.new(item).update
      end
    end
  end

  class Base
    def initialize item
      @item = item
    end
  end

  class Backstage < Base
    def update
      @item.sell_in -= 1
      @item.quality += 1

      @item.quality += 1 if @item.sell_in <= 10
      @item.quality += 1 if @item.sell_in <= 5
      @item.quality = 0 if @item.sell_in <= 0
    end
  end

  class Sulfuras < Base
    def update
      @item.sell_in -= 1
    end
  end

  class AgedBrie < Base
    def update
      @item.sell_in -= 1
      @item.quality += 1 unless @item.quality == 50

      if @item.sell_in <= 0
        @item.quality += 1 unless @item.quality == 50
      end
    end
  end

  class Default < Base
    def update
      @item.sell_in -= 1
      @item.quality -= 1 unless @item.quality == 0

      if @item.sell_in <= 0
        @item.quality -= 1 unless @item.quality == 0
      end
    end
  end

  GILDED_CLASSES = {
    "Aged Brie" => AgedBrie,
    "Sulfuras, Hand of Ragnaros" => Sulfuras,
    "Backstage passes to a TAFKAL80ETC concert" => Backstage
  }

end

class Item
  attr_accessor :name, :sell_in, :quality

  def initialize(name, sell_in, quality)
    @name = name
    @sell_in = sell_in
    @quality = quality
  end

  def to_s()
    "#{@name}, #{@sell_in}, #{@quality}"
  end
end
