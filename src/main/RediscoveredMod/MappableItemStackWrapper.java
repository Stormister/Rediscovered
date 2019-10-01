//	  Copyright 2012-2014 Matthew Karcz
//
//	  This file is part of The Rediscovered Mod.
//
//    The Rediscovered Mod is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    The Rediscovered Mod is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with The Rediscovered Mod.  If not, see <http://www.gnu.org/licenses/>.












package RediscoveredMod;

import net.minecraft.item.ItemStack;

public class MappableItemStackWrapper {
    private ItemStack wrap;

    public MappableItemStackWrapper(ItemStack toWrap)
    {
        wrap = toWrap;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof MappableItemStackWrapper)) return false;
        MappableItemStackWrapper isw = (MappableItemStackWrapper) obj;
        if (wrap.getHasSubtypes())
        {
            return isw.wrap.isItemEqual(wrap);
        }
        else
        {
            return isw.wrap.itemID == wrap.itemID;
        }
    }

    @Override
    public int hashCode()
    {
        return wrap.itemID;
    }
}