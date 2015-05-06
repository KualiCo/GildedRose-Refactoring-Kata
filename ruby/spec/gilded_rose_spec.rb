require 'spec_helper'
require File.join(File.dirname(__FILE__), '../gilded_rose')

describe GildedRose do
  describe "#update_quality" do
    let(:items) { [] }

    context "Default case" do
      let(:name)  { "Default" }

      context "Sell in 10, quality 10" do
        it "reduces sell_in and quality by 1" do
          cycle(items, name, 10, 10)
          expect(items.first.sell_in).to eq 9
          expect(items.first.quality).to eq 9
        end
      end

      context "Quality 0" do
        it "leaves quality at 0" do
          cycle(items, name, 10, 0)
          expect(items.first.quality).to eq 0
        end
      end

      context "Sell in 0" do
        it "reduces quality by 2" do
          cycle(items, name, 0, 20)
          expect(items.first.quality).to eq 18
        end

        it "reduces quality to 0 if quality is 1" do
          cycle(items, name, 0, 1)
          expect(items.first.quality).to eq 0
        end
      end
    end

    context "Aged Brie" do
      let(:name) { "Aged Brie" }

      context "Sell in 10, quality 10" do
        it "increases quality by 1" do
          cycle(items, name, 10, 10)
          expect(items.first.quality).to eq 11
        end
      end

      context "Sell in 0, quality 10" do
        it "increases quality by 2" do
          cycle(items, name, 0, 10)
          expect(items.first.quality).to eq 12
        end
      end

      context "Sell in 10, quality 50" do
        it "leaves quality at 50" do
          cycle(items, name, 10, 50)
          expect(items.first.quality).to eq 50
        end
      end
    end

    context "Sulfuras, Hand of Ragnaros" do
      let(:name) { "Sulfuras, Hand of Ragnaros" }

      context "Sell in 10, quality 80" do
        it "leaves quality at 80" do
          cycle(items, name, 10, 80)
          expect(items.first.quality).to eq 80
        end
      end

      context "Sell in 0, quality 80" do
        it "leaves quality at 80" do
          cycle(items, name, 10, 80)
          expect(items.first.quality).to eq 80
        end
      end
    end

    context "Backstage passes to a TAFKAL80ETC concert" do
      let(:name) { "Backstage passes to a TAFKAL80ETC concert" }

      context "Sell in 20, quality 20" do
        it "increases quality by 1" do
          cycle(items, name, 20, 20)
          expect(items.first.quality).to eq 21
        end
      end

      context "Sell in 10, quality 20" do
        it "increases quality by 2" do
          cycle(items, name, 10, 20)
          expect(items.first.quality).to eq 22
        end
      end

      context "Sell in 5, quality 20" do
        it "increases quality by 3" do
          cycle(items, name, 5, 20)
          expect(items.first.quality).to eq 23
        end
      end

      context "Sell in 0, quality 20" do
        it "returns quality 0" do
          cycle(items, name, 0, 20)
          expect(items.first.quality).to eq 0
        end
      end
    end
  end
end

describe Item do
  it "returns correct name" do
    item = Item.new("foo", 1, 1)
    expect(item.to_s).to eq "foo, 1, 1"
  end
end

def cycle(items, name, sell_in, quality)
  items << Item.new(name, sell_in, quality)
  GildedRose.new(items).update_quality
end
