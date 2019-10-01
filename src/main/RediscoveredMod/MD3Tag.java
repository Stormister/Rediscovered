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

import net.minecraft.util.Vec3;

public final class MD3Tag
{
  public String name;
  public Vec3[] coords;
  public Vec3[] c;
  public Vec3[] d;
  public Vec3[] e;

  public MD3Tag(int var1)
  {
    this.coords = new Vec3[var1];
    this.c = new Vec3[var1];
    this.d = new Vec3[var1];
    this.e = new Vec3[var1];
  }
}