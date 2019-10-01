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

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

public class ModelTestDragon extends ModelBase
{
  //fields
    ModelRenderer Body1;
    ModelRenderer Head;
    ModelRenderer LowerLip;
    ModelRenderer UpperLip;
    ModelRenderer Snout;
    ModelRenderer TopMouth;
    ModelRenderer BottomMouth;
    ModelRenderer LeftFrontHorn;
    ModelRenderer Nose;
    ModelRenderer Chin;
    ModelRenderer LowerChin;
    ModelRenderer Throat;
    ModelRenderer LeftBottomHorn;
    ModelRenderer RightBottomHorn;
    ModelRenderer RightUpperHorn;
    ModelRenderer Tooth1;
    ModelRenderer Tooth2;
    ModelRenderer Tooth3;
    ModelRenderer Tooth4;
    ModelRenderer Tooth5;
    ModelRenderer Tooth6;
    ModelRenderer Tooth7;
    ModelRenderer Tooth8;
    ModelRenderer Tooth9;
    ModelRenderer Tooth10;
    ModelRenderer Tooth11;
    ModelRenderer Tooth12;
    ModelRenderer Tooth13;
    ModelRenderer Tooth14;
    ModelRenderer Tooth15;
    ModelRenderer Tooth16;
    ModelRenderer Tooth17;
    ModelRenderer Tooth18;
    ModelRenderer Tooth19;
    ModelRenderer Tooth20;
    ModelRenderer Tooth21;
    ModelRenderer Tooth22;
    ModelRenderer Tooth23;
    ModelRenderer Tooth24;
    ModelRenderer Neck1;
    ModelRenderer Neck2;
    ModelRenderer Neck3;
    ModelRenderer NeckBase;
    ModelRenderer Body2;
    ModelRenderer Body3;
    ModelRenderer TailBase;
    ModelRenderer Tail1;
    ModelRenderer Tail2;
    ModelRenderer Tail3;
    ModelRenderer Tail4;
    ModelRenderer Tail5;
    ModelRenderer Tail6;
    ModelRenderer Tail7;
    ModelRenderer Tail8;
    ModelRenderer Tail9;
    ModelRenderer LeftWingBone1;
    ModelRenderer LeftWingBone2;
    ModelRenderer LeftWingBone3;
    ModelRenderer LeftWing1;
    ModelRenderer LeftWing2;
    ModelRenderer LeftWing3;
    ModelRenderer RightWingBone1;
    ModelRenderer RightWingBone2;
    ModelRenderer RightWingBone3;
    ModelRenderer RightWing3;
    ModelRenderer RightWing2;
    ModelRenderer RightWing1;
    ModelRenderer RightHorn1;
    ModelRenderer RightHorn2;
    ModelRenderer RightHorn3;
    ModelRenderer LeftHorn1;
    ModelRenderer LeftHorn2;
    ModelRenderer LeftHorn3;
    ModelRenderer Scale1;
    ModelRenderer Scale2;
    ModelRenderer Scale3;
    ModelRenderer Scale4;
    
    private float partialTicks;
  
  public ModelTestDragon()
  {
    textureWidth = 912;
    textureHeight = 912;
    
    Body1 = new ModelRenderer(this, 174, 181);
    Body1.addBox(-1F, 0F, 0F, 34, 34, 23);
    Body1.setRotationPoint(-4F, -61F, 59F);
    Body1.setTextureSize(912, 912);
    Body1.mirror = true;
    setRotation(Body1, 0F, 0F, 0.8F);
    Head = new ModelRenderer(this, 173, 121);
    Head.addBox(0F, 0F, 0F, 25, 26, 28);
    Head.setRotationPoint(-17F, -51F, -74F);
    Head.setTextureSize(912, 912);
    Head.mirror = true;
    setRotation(Head, 0F, 0F, 0F);
    LowerLip = new ModelRenderer(this, 9, 116);
    LowerLip.addBox(0F, 0F, 0F, 25, 4, 31);
    LowerLip.setRotationPoint(-17F, -29F, -105F);
    LowerLip.setTextureSize(912, 912);
    LowerLip.mirror = true;
    setRotation(LowerLip, 0F, 0F, 0F);
    UpperLip = new ModelRenderer(this, 10, 72);
    UpperLip.addBox(0F, 0F, 0F, 25, 4, 31);
    UpperLip.setRotationPoint(-17F, -43F, -105F);
    UpperLip.setTextureSize(912, 912);
    UpperLip.mirror = true;
    setRotation(UpperLip, 0F, 0F, 0F);
    Snout = new ModelRenderer(this, 363, 570);
    Snout.addBox(0F, 0F, 0F, 25, 4, 23);
    Snout.setRotationPoint(-17F, -47F, -95F);
    Snout.setTextureSize(912, 912);
    Snout.mirror = true;
    setRotation(Snout, 0F, 0F, 0F);
    TopMouth = new ModelRenderer(this, 364, 540);
    TopMouth.addBox(0F, 0F, 0F, 25, 17, 7);
    TopMouth.setRotationPoint(-17F, -40F, -87F);
    TopMouth.setTextureSize(912, 912);
    TopMouth.mirror = true;
    setRotation(TopMouth, 1F, 0F, 0F);
    BottomMouth = new ModelRenderer(this, 365, 512);
    BottomMouth.addBox(0F, 0F, 0F, 25, 5, 15);
    BottomMouth.setRotationPoint(-17F, -29F, -83F);
    BottomMouth.setTextureSize(912, 912);
    BottomMouth.mirror = true;
    setRotation(BottomMouth, 0.5F, 0F, 0F);
    LeftFrontHorn = new ModelRenderer(this, 127, 109);
    LeftFrontHorn.addBox(0F, 0F, 0F, 4, 39, 4);
    LeftFrontHorn.setRotationPoint(14F, -57F, -57F);
    LeftFrontHorn.setTextureSize(912, 912);
    LeftFrontHorn.mirror = true;
    setRotation(LeftFrontHorn, -1.3F, 0.3F, 0F);
    Nose = new ModelRenderer(this, 366, 470);
    Nose.addBox(0F, 0F, 0F, 25, 12, 4);
    Nose.setRotationPoint(-17F, -47F, -94F);
    Nose.setTextureSize(912, 912);
    Nose.mirror = true;
    setRotation(Nose, -1.23F, 0F, 0F);
    Chin = new ModelRenderer(this, 365, 453);
    Chin.addBox(0F, 0F, 0F, 25, 5, 5);
    Chin.setRotationPoint(-17F, -25F, -105F);
    Chin.setTextureSize(912, 912);
    Chin.mirror = true;
    setRotation(Chin, 0.8F, 0F, 0F);
    LowerChin = new ModelRenderer(this, 171, 82);
    LowerChin.addBox(0F, 0F, 0F, 25, 3, 28);
    LowerChin.setRotationPoint(-17F, -25F, -102F);
    LowerChin.setTextureSize(912, 912);
    LowerChin.mirror = true;
    setRotation(LowerChin, 0F, 0F, 0F);
    Throat = new ModelRenderer(this, 365, 427);
    Throat.addBox(0F, 0F, 0F, 25, 3, 16);
    Throat.setRotationPoint(-17F, -25F, -75F);
    Throat.setTextureSize(912, 912);
    Throat.mirror = true;
    setRotation(Throat, 0.2F, 0F, 0F);
    LeftBottomHorn = new ModelRenderer(this, 127, 87);
    LeftBottomHorn.addBox(0F, 0F, 0F, 4, 13, 4);
    LeftBottomHorn.setRotationPoint(3F, -28F, -49F);
    LeftBottomHorn.setTextureSize(912, 912);
    LeftBottomHorn.mirror = true;
    setRotation(LeftBottomHorn, 1F, 1F, 0F);
    RightBottomHorn = new ModelRenderer(this, 127, 65);
    RightBottomHorn.addBox(0F, 0F, 0F, 4, 13, 4);
    RightBottomHorn.setRotationPoint(-15F, -31F, -47F);
    RightBottomHorn.setTextureSize(912, 912);
    RightBottomHorn.mirror = true;
    setRotation(RightBottomHorn, -1F, 2F, 0F);
    RightUpperHorn = new ModelRenderer(this, 149, 112);
    RightUpperHorn.addBox(-1F, 0F, 1F, 4, 36, 4);
    RightUpperHorn.setRotationPoint(-25F, -57F, -61F);
    RightUpperHorn.setTextureSize(912, 912);
    RightUpperHorn.mirror = true;
    setRotation(RightUpperHorn, -1.3F, -0.3F, 0F);
    Tooth1 = new ModelRenderer(this, 91, 57);
    Tooth1.addBox(0F, 0F, 0F, 3, 4, 3);
    Tooth1.setRotationPoint(5F, -33F, -105F);
    Tooth1.setTextureSize(912, 912);
    Tooth1.mirror = true;
    setRotation(Tooth1, 0F, 0F, 0F);
    Tooth2 = new ModelRenderer(this, 90, 48);
    Tooth2.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth2.setRotationPoint(6F, -32F, -101F);
    Tooth2.setTextureSize(912, 912);
    Tooth2.mirror = true;
    setRotation(Tooth2, 0F, 0F, 0F);
    Tooth3 = new ModelRenderer(this, 90, 38);
    Tooth3.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth3.setRotationPoint(6F, -32F, -97F);
    Tooth3.setTextureSize(912, 912);
    Tooth3.mirror = true;
    setRotation(Tooth3, 0F, 0F, 0F);
    Tooth4 = new ModelRenderer(this, 90, 28);
    Tooth4.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth4.setRotationPoint(6F, -32F, -93F);
    Tooth4.setTextureSize(912, 912);
    Tooth4.mirror = true;
    setRotation(Tooth4, 0F, 0F, 0F);
    Tooth5 = new ModelRenderer(this, 90, 17);
    Tooth5.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth5.setRotationPoint(6F, -32F, -89F);
    Tooth5.setTextureSize(912, 912);
    Tooth5.mirror = true;
    setRotation(Tooth5, 0F, 0F, 0F);
    Tooth6 = new ModelRenderer(this, 77, 60);
    Tooth6.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth6.setRotationPoint(-17F, -32F, -101F);
    Tooth6.setTextureSize(912, 912);
    Tooth6.mirror = true;
    setRotation(Tooth6, 0F, 0F, 0F);
    Tooth7 = new ModelRenderer(this, 76, 51);
    Tooth7.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth7.setRotationPoint(-17F, -32F, -97F);
    Tooth7.setTextureSize(912, 912);
    Tooth7.mirror = true;
    setRotation(Tooth7, 0F, 0F, 0F);
    Tooth8 = new ModelRenderer(this, 76, 43);
    Tooth8.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth8.setRotationPoint(-17F, -32F, -93F);
    Tooth8.setTextureSize(912, 912);
    Tooth8.mirror = true;
    setRotation(Tooth8, 0F, 0F, 0F);
    Tooth9 = new ModelRenderer(this, 76, 34);
    Tooth9.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth9.setRotationPoint(-17F, -32F, -89F);
    Tooth9.setTextureSize(912, 912);
    Tooth9.mirror = true;
    setRotation(Tooth9, 0F, 0F, 0F);
    Tooth10 = new ModelRenderer(this, 71, 11);
    Tooth10.addBox(0F, 0F, 0F, 3, 4, 3);
    Tooth10.setRotationPoint(-17F, -33F, -105F);
    Tooth10.setTextureSize(912, 912);
    Tooth10.mirror = true;
    setRotation(Tooth10, 0F, 0F, 0F);
    Tooth11 = new ModelRenderer(this, 61, 39);
    Tooth11.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth11.setRotationPoint(-13F, -32F, -105F);
    Tooth11.setTextureSize(912, 912);
    Tooth11.mirror = true;
    setRotation(Tooth11, 0F, 0F, 0F);
    Tooth12 = new ModelRenderer(this, 46, 60);
    Tooth12.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth12.setRotationPoint(2F, -32F, -105F);
    Tooth12.setTextureSize(912, 912);
    Tooth12.mirror = true;
    setRotation(Tooth12, 0F, 0F, 0F);
    Tooth13 = new ModelRenderer(this, 41, 46);
    Tooth13.addBox(0F, 0F, 0F, 3, 4, 3);
    Tooth13.setRotationPoint(5F, -39F, -105F);
    Tooth13.setTextureSize(912, 912);
    Tooth13.mirror = true;
    setRotation(Tooth13, 0F, 0F, 0F);
    Tooth14 = new ModelRenderer(this, 41, 33);
    Tooth14.addBox(0F, 0F, 0F, 3, 4, 3);
    Tooth14.setRotationPoint(-17F, -39F, -105F);
    Tooth14.setTextureSize(912, 912);
    Tooth14.mirror = true;
    setRotation(Tooth14, 0F, 0F, 0F);
    Tooth15 = new ModelRenderer(this, 37, 19);
    Tooth15.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth15.setRotationPoint(2F, -39F, -105F);
    Tooth15.setTextureSize(912, 912);
    Tooth15.mirror = true;
    setRotation(Tooth15, 0F, 0F, 0F);
    Tooth16 = new ModelRenderer(this, 25, 39);
    Tooth16.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth16.setRotationPoint(-13F, -39F, -104.5F);
    Tooth16.setTextureSize(912, 912);
    Tooth16.mirror = true;
    setRotation(Tooth16, 0F, 0F, 0F);
    Tooth17 = new ModelRenderer(this, 23, 28);
    Tooth17.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth17.setRotationPoint(6F, -39F, -101F);
    Tooth17.setTextureSize(912, 912);
    Tooth17.mirror = true;
    setRotation(Tooth17, 0F, 0F, 0F);
    Tooth18 = new ModelRenderer(this, 21, 17);
    Tooth18.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth18.setRotationPoint(6F, -39F, -97F);
    Tooth18.setTextureSize(912, 912);
    Tooth18.mirror = true;
    setRotation(Tooth18, 0F, 0F, 0F);
    Tooth19 = new ModelRenderer(this, 19, 6);
    Tooth19.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth19.setRotationPoint(6F, -39F, -93F);
    Tooth19.setTextureSize(912, 912);
    Tooth19.mirror = true;
    setRotation(Tooth19, 0F, 0F, 0F);
    Tooth20 = new ModelRenderer(this, 15, 60);
    Tooth20.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth20.setRotationPoint(6F, -39F, -89F);
    Tooth20.setTextureSize(912, 912);
    Tooth20.mirror = true;
    setRotation(Tooth20, 0F, 0F, 0F);
    Tooth21 = new ModelRenderer(this, 12, 50);
    Tooth21.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth21.setRotationPoint(-17F, -39F, -101F);
    Tooth21.setTextureSize(912, 912);
    Tooth21.mirror = true;
    setRotation(Tooth21, 0F, 0F, 0F);
    Tooth22 = new ModelRenderer(this, 9, 39);
    Tooth22.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth22.setRotationPoint(-17F, -39F, -97F);
    Tooth22.setTextureSize(912, 912);
    Tooth22.mirror = true;
    setRotation(Tooth22, 0F, 0F, 0F);
    Tooth23 = new ModelRenderer(this, 8, 26);
    Tooth23.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth23.setRotationPoint(-17F, -39F, -93F);
    Tooth23.setTextureSize(912, 912);
    Tooth23.mirror = true;
    setRotation(Tooth23, 0F, 0F, 0F);
    Tooth24 = new ModelRenderer(this, 6, 15);
    Tooth24.addBox(0F, 0F, 0F, 2, 3, 2);
    Tooth24.setRotationPoint(-17F, -39F, -89F);
    Tooth24.setTextureSize(912, 912);
    Tooth24.mirror = true;
    setRotation(Tooth24, 0F, 0F, 0F);
    Neck1 = new ModelRenderer(this, 463, 855);
    Neck1.addBox(-1F, 0F, 0F, 18, 20, 28);
    Neck1.setRotationPoint(-3F, -51F, -46F);
    Neck1.setTextureSize(912, 912);
    Neck1.mirror = true;
    setRotation(Neck1, 0F, 0F, 0.8F);
    Neck2 = new ModelRenderer(this, 463, 802);
    Neck2.addBox(-1F, 0F, 0F, 18, 20, 28);
    Neck2.setRotationPoint(-3F, -51F, -18F);
    Neck2.setTextureSize(912, 912);
    Neck2.mirror = true;
    setRotation(Neck2, 0F, 0F, 0.8F);
    Neck3 = new ModelRenderer(this, 461, 747);
    Neck3.addBox(-1F, 0F, 0F, 18, 20, 28);
    Neck3.setRotationPoint(-3F, -51F, 10F);
    Neck3.setTextureSize(912, 912);
    Neck3.mirror = true;
    setRotation(Neck3, 0F, 0F, 0.8F);
    NeckBase = new ModelRenderer(this, 461, 695);
    NeckBase.addBox(-1F, 0F, 0F, 22, 24, 21);
    NeckBase.setRotationPoint(-3F, -54F, 38F);
    NeckBase.setTextureSize(912, 912);
    NeckBase.mirror = true;
    setRotation(NeckBase, 0F, 0F, 0.8F);
    Body2 = new ModelRenderer(this, 12, 160);
    Body2.addBox(-1F, 0F, 0F, 37, 37, 41);
    Body2.setRotationPoint(-4F, -63F, 79F);
    Body2.setTextureSize(912, 912);
    Body2.mirror = true;
    setRotation(Body2, 0F, 0F, 0.8F);
    Body3 = new ModelRenderer(this, 462, 639);
    Body3.addBox(-1F, 0F, 0F, 34, 34, 15);
    Body3.setRotationPoint(-4F, -61F, 120F);
    Body3.setTextureSize(912, 912);
    Body3.mirror = true;
    setRotation(Body3, 0F, 0F, 0.8F);
    TailBase = new ModelRenderer(this, 466, 586);
    TailBase.addBox(0F, 0F, 0F, 22, 24, 21);
    TailBase.setRotationPoint(-4F, -54F, 135F);
    TailBase.setTextureSize(912, 912);
    TailBase.mirror = true;
    setRotation(TailBase, 0F, 0F, 0.8F);
    Tail1 = new ModelRenderer(this, 466, 530);
    Tail1.addBox(0F, 0F, 0F, 18, 20, 28);
    Tail1.setRotationPoint(-4F, -51F, 154F);
    Tail1.setTextureSize(912, 912);
    Tail1.mirror = true;
    setRotation(Tail1, 0F, 0F, 0.8F);
    Tail2 = new ModelRenderer(this, 460, 474);
    Tail2.addBox(0F, 0F, 0F, 18, 20, 28);
    Tail2.setRotationPoint(-4F, -51F, 182F);
    Tail2.setTextureSize(912, 912);
    Tail2.mirror = true;
    setRotation(Tail2, 0F, 0F, 0.8F);
    Tail3 = new ModelRenderer(this, 458, 421);
    Tail3.addBox(0F, 0F, 0F, 18, 20, 28);
    Tail3.setRotationPoint(-4F, -51F, 210F);
    Tail3.setTextureSize(912, 912);
    Tail3.mirror = true;
    setRotation(Tail3, 0F, 0F, 0.8F);
    Tail4 = new ModelRenderer(this, 361, 368);
    Tail4.addBox(0F, 0F, 0F, 18, 20, 28);
    Tail4.setRotationPoint(-4F, -51F, 238F);
    Tail4.setTextureSize(912, 912);
    Tail4.mirror = true;
    setRotation(Tail4, 0F, 0F, 0.8F);
    Tail5 = new ModelRenderer(this, 361, 311);
    Tail5.addBox(0F, 0F, 0F, 18, 20, 28);
    Tail5.setRotationPoint(-4F, -51F, 266F);
    Tail5.setTextureSize(912, 912);
    Tail5.mirror = true;
    setRotation(Tail5, 0F, 0F, 0.8F);
    Tail6 = new ModelRenderer(this, 364, 259);
    Tail6.addBox(0F, 0F, 0F, 14, 16, 28);
    Tail6.setRotationPoint(-4F, -48F, 294F);
    Tail6.setTextureSize(912, 912);
    Tail6.mirror = true;
    setRotation(Tail6, 0F, 0F, 0.8F);
    Tail7 = new ModelRenderer(this, 366, 212);
    Tail7.addBox(0F, 0F, 0F, 11, 13, 28);
    Tail7.setRotationPoint(-4F, -46F, 322F);
    Tail7.setTextureSize(912, 912);
    Tail7.mirror = true;
    setRotation(Tail7, 0F, 0F, 0.8F);
    Tail8 = new ModelRenderer(this, 369, 168);
    Tail8.addBox(0F, 0F, 0F, 9, 11, 28);
    Tail8.setRotationPoint(-4F, -45F, 350F);
    Tail8.setTextureSize(912, 912);
    Tail8.mirror = true;
    setRotation(Tail8, 0F, 0F, 0.8F);
    Tail9 = new ModelRenderer(this, 371, 126);
    Tail9.addBox(0F, 0F, 0F, 7, 9, 28);
    Tail9.setRotationPoint(-4F, -44F, 378F);
    Tail9.setTextureSize(912, 912);
    Tail9.mirror = true;
    setRotation(Tail9, 0F, 0F, 0.8F);
    
    
    LeftWingBone1 = new ModelRenderer(this, 314, 305);
    LeftWingBone1.addBox(-6.5F, -2.5F, 0F, 7, 75, 7);
    LeftWingBone1.setRotationPoint(2F, -57F, 65F);
    LeftWingBone1.setTextureSize(912, 912);
    LeftWingBone1.mirror = true;
    setRotation(LeftWingBone1, 0F, 0F, -2.25F);
    
    LeftWingBone2 = new ModelRenderer(this, 149, 60);
    LeftWingBone2.addBox(56F, -46F, 0F, 75, 7, 7);
    LeftWingBone2.setRotationPoint(2F, -57F, 65F);
    LeftWingBone2.setTextureSize(912, 912);
    LeftWingBone2.mirror = true;
    setRotation(LeftWingBone2, 0F, 0F, 0F);
    
    LeftWingBone3 = new ModelRenderer(this, 147, 41);
    LeftWingBone3.addBox(126F, -46F, 0F, 75, 7, 7);
    LeftWingBone3.setRotationPoint(2F, -57F, 65F);
    LeftWingBone3.setTextureSize(912, 912);
    LeftWingBone3.mirror = true;
    setRotation(LeftWingBone3, 0F, 0F, 0F);
    
    
    LeftWing1 = new ModelRenderer(this, 10, 245);
    LeftWing1.addBox(0F, 0F, 0F, 0, 72, 70);
    LeftWing1.setRotationPoint(2F, -57F, 65F);
    LeftWing1.setTextureSize(912, 912);
    LeftWing1.mirror = true;
    setRotation(LeftWing1, 0F, 0F, -2.25F);
    LeftWing2 = new ModelRenderer(this, 8, 392);
    LeftWing2.addBox(56F, -45.2F, 0F, 74, 0, 100);
    LeftWing2.setRotationPoint(2F, -57F, 65F);
    LeftWing2.setTextureSize(912, 912);
    LeftWing2.mirror = true;
    setRotation(LeftWing2, 0F, 0F, 0F);
    LeftWing3 = new ModelRenderer(this, 5, 758);
    LeftWing3.addBox(126F, -45.2F, 0F, 75, 0, 150);
    LeftWing3.setRotationPoint(2F, -57F, 65F);
    LeftWing3.setTextureSize(912, 912);
    LeftWing3.mirror = true;
    setRotation(LeftWing3, 0F, 0F, 0F);
    
    
    
    RightWingBone1 = new ModelRenderer(this, 300, 216);
    RightWingBone1.addBox(-1F, -3F, 0F, 7, 75, 7);
    RightWingBone1.setRotationPoint(-10F, -57F, 60F);
    RightWingBone1.setTextureSize(912, 912);
    RightWingBone1.mirror = true;
    setRotation(RightWingBone1, 0F, 0F, 2.25F);
    
    RightWingBone2 = new ModelRenderer(this, 149, 23);
    RightWingBone2.addBox(-130F, -46F, 0F, 75, 7, 7);
    RightWingBone2.setRotationPoint(-10F, -57F, 60F);
    RightWingBone2.setTextureSize(912, 912);
    RightWingBone2.mirror = true;
    setRotation(RightWingBone2, 0F, 0F, 0F);
    
    RightWingBone3 = new ModelRenderer(this, 150, 6);
    RightWingBone3.addBox(-200F, -46F, 0F, 75, 7, 7);
    RightWingBone3.setRotationPoint(-10F, -57F, 60F);
    RightWingBone3.setTextureSize(912, 912);
    RightWingBone3.mirror = true;
    setRotation(RightWingBone3, 0F, 0F, 0F);
    
    
    
    RightWing1 = new ModelRenderer(this, 155, 245);
    RightWing1.addBox(0F, 0F, 0F, 0, 72, 70);
    RightWing1.setRotationPoint(-10F, -57F, 60F);
    RightWing1.setTextureSize(912, 912);
    RightWing1.mirror = true;
    setRotation(RightWing1, 0F, 0F, 2.25F);
    
    RightWing2 = new ModelRenderer(this, 8, 498);
    RightWing2.addBox(-130F, -45.2F, 0F, 74, 0, 100);
    RightWing2.setRotationPoint(-10F, -57F, 60F);
    RightWing2.setTextureSize(912, 912);
    RightWing2.mirror = true;
    setRotation(RightWing2, 0F, 0F, 0F);
    //RightWing1.addChild(RightWing2);
    
    RightWing3 = new ModelRenderer(this, 5, 603);
    RightWing3.addBox(-200F, -45.2F, 0F, 75, 0, 150);
    RightWing3.setRotationPoint(-10F, -57F, 60F);
    RightWing3.setTextureSize(912, 912);
    RightWing3.mirror = true;
    setRotation(RightWing3, 0F, 0F, 0F);
    //RightWing2.addChild(RightWing3);
    
    
    
    
    RightHorn1 = new ModelRenderer(this, 369, 91);
    RightHorn1.addBox(0F, 0F, 0F, 10, 19, 10);
    RightHorn1.setRotationPoint(-23F, -51F, -54F);
    RightHorn1.setTextureSize(912, 912);
    RightHorn1.mirror = true;
    setRotation(RightHorn1, -1F, -1F, 0F);
    RightHorn2 = new ModelRenderer(this, 324, 37);
    RightHorn2.addBox(0F, 0F, 0F, 8, 37, 8);
    RightHorn2.setRotationPoint(-34.2F, -68F, -25F);
    RightHorn2.setTextureSize(912, 912);
    RightHorn2.mirror = true;
    setRotation(RightHorn2, -1F, -0.4F, 0F);
    RightHorn3 = new ModelRenderer(this, 363, 42);
    RightHorn3.addBox(0F, 0F, 0F, 6, 37, 6);
    RightHorn3.setRotationPoint(-45F, -86F, 3F);
    RightHorn3.setTextureSize(912, 912);
    RightHorn3.mirror = true;
    setRotation(RightHorn3, -1F, -0.4F, 0F);
    LeftHorn1 = new ModelRenderer(this, 294, 180);
    LeftHorn1.addBox(0F, 0F, 0F, 10, 19, 10);
    LeftHorn1.setRotationPoint(19F, -42F, -49.5F);
    LeftHorn1.setTextureSize(912, 912);
    LeftHorn1.mirror = true;
    setRotation(LeftHorn1, 1F, 4F, 0F);
    LeftHorn2 = new ModelRenderer(this, 286, 130);
    LeftHorn2.addBox(0F, 0F, 0F, 8, 37, 8);
    LeftHorn2.setRotationPoint(32F, -61F, -24F);
    LeftHorn2.setTextureSize(912, 912);
    LeftHorn2.mirror = true;
    setRotation(LeftHorn2, 1F, 3.7F, 0F);
    LeftHorn3 = new ModelRenderer(this, 325, 131);
    LeftHorn3.addBox(0F, 0F, 0F, 6, 37, 6);
    LeftHorn3.setRotationPoint(46F, -82F, 2F);
    LeftHorn3.setTextureSize(912, 912);
    LeftHorn3.mirror = true;
    setRotation(LeftHorn3, 1F, 3.7F, 0F);
    Scale1 = new ModelRenderer(this, 115, 50);
    Scale1.addBox(0F, 0F, 0F, 6, 6, 6);
    Scale1.setRotationPoint(-4F, -49F, -95F);
    Scale1.setTextureSize(912, 912);
    Scale1.mirror = true;
    setRotation(Scale1, 0F, 0F, 0.8F);
    Scale2 = new ModelRenderer(this, 113, 30);
    Scale2.addBox(0F, 0F, 0F, 6, 6, 6);
    Scale2.setRotationPoint(-4F, -51F, -89F);
    Scale2.setTextureSize(912, 912);
    Scale2.mirror = true;
    setRotation(Scale2, 0F, 0F, 0.8F);
    Scale3 = new ModelRenderer(this, 113, 8);
    Scale3.addBox(0F, 0F, 0F, 9, 9, 6);
    Scale3.setRotationPoint(-4F, -53F, -83F);
    Scale3.setTextureSize(912, 912);
    Scale3.mirror = true;
    setRotation(Scale3, 0F, 0F, 0.8F);
    Scale4 = new ModelRenderer(this, 464, 368);
    Scale4.addBox(0F, 0F, 0F, 11, 11, 31);
    Scale4.setRotationPoint(-4F, -54F, -77F);
    Scale4.setTextureSize(912, 912);
    Scale4.mirror = true;
    setRotation(Scale4, 0F, 0F, 0.8F);
  }
  
  /**
   * Used for easily adding entity-dependent animations. The second and third float params here are the same second
   * and third as in the setRotationAngles method.
   */
  public void setLivingAnimations(EntityLiving par1EntityLiving, float par2, float par3, float par4)
  {
      this.partialTicks = par4;
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(entity, f, f1, f2, f3, f4, f5);
    Body1.render(f5);
    Head.render(f5);
    LowerLip.render(f5);
    UpperLip.render(f5);
    Snout.render(f5);
    TopMouth.render(f5);
    BottomMouth.render(f5);
    LeftFrontHorn.render(f5);
    Nose.render(f5);
    Chin.render(f5);
    LowerChin.render(f5);
    Throat.render(f5);
    LeftBottomHorn.render(f5);
    RightBottomHorn.render(f5);
    RightUpperHorn.render(f5);
    Tooth1.render(f5);
    Tooth2.render(f5);
    Tooth3.render(f5);
    Tooth4.render(f5);
    Tooth5.render(f5);
    Tooth6.render(f5);
    Tooth7.render(f5);
    Tooth8.render(f5);
    Tooth9.render(f5);
    Tooth10.render(f5);
    Tooth11.render(f5);
    Tooth12.render(f5);
    Tooth13.render(f5);
    Tooth14.render(f5);
    Tooth15.render(f5);
    Tooth16.render(f5);
    Tooth17.render(f5);
    Tooth18.render(f5);
    Tooth19.render(f5);
    Tooth20.render(f5);
    Tooth21.render(f5);
    Tooth22.render(f5);
    Tooth23.render(f5);
    Tooth24.render(f5);
    Neck1.render(f5);
    Neck2.render(f5);
    Neck3.render(f5);
    NeckBase.render(f5);
    Body2.render(f5);
    Body3.render(f5);
    TailBase.render(f5);
    Tail1.render(f5);
    Tail2.render(f5);
    Tail3.render(f5);
    Tail4.render(f5);
    Tail5.render(f5);
    Tail6.render(f5);
    Tail7.render(f5);
    Tail8.render(f5);
    Tail9.render(f5);
    LeftWingBone1.render(f5);
    LeftWingBone2.render(f5);
    LeftWingBone3.render(f5);
    LeftWing1.render(f5);
    LeftWing2.render(f5);
    LeftWing3.render(f5);
    RightWingBone1.render(f5);
    RightWingBone2.render(f5);
    RightWingBone3.render(f5);
    RightWing1.render(f5);
    RightWing2.render(f5);
    RightWing3.render(f5);
    RightHorn1.render(f5);
    RightHorn2.render(f5);
    RightHorn3.render(f5);
    LeftHorn1.render(f5);
    LeftHorn2.render(f5);
    LeftHorn3.render(f5);
    Scale1.render(f5);
    Scale2.render(f5);
    Scale3.render(f5);
    Scale4.render(f5);   
    
  }
  
  /**
   * Updates the rotations in the parameters for rotations greater than 180 degrees or less than -180 degrees. It adds
   * or subtracts 360 degrees, so that the appearance is the same, although the numbers are then simplified to range
   * -180 to 180
   */
  private float updateRotations(double par1)
  {
      while (par1 >= 180.0D)
      {
          par1 -= 360.0D;
      }

      while (par1 < -180.0D)
      {
          par1 += 360.0D;
      }

      return (float)par1;
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(Entity entity, float par1, float par2, float par3, float par4, float par5, float par6)
  {
	  EntityTestDragon var8 = (EntityTestDragon)entity;
	  //par1 = time			par2 = walk speed			par3 = flying
	  
	  RightWing1.rotateAngleZ = par3 + 2.25F;
	  RightWing2.rotateAngleZ = par3;
	  RightWing3.rotateAngleZ = par3;
//	  RightWing1.rotateAngleZ = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2 + 2.25F;
//	  RightWing2.rotateAngleZ = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
//	  RightWing3.rotateAngleZ = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	  
	  RightWingBone1.rotateAngleZ = par3 + 2.25F;
	  RightWingBone2.rotateAngleZ = par3;
	  RightWingBone3.rotateAngleZ = par3;
//	  RightWingBone1.rotateAngleZ = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2 + 2.25F;
//	  RightWingBone2.rotateAngleZ = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
//	  RightWingBone3.rotateAngleZ = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	  
	  LeftWing1.rotateAngleZ = -par3 - 2.25F;
	  LeftWing2.rotateAngleZ = -par3;
	  LeftWing3.rotateAngleZ = -par3;
//	  LeftWing1.rotateAngleZ = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2 - 2.25F;
//	  LeftWing2.rotateAngleZ = -MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
//	  LeftWing3.rotateAngleZ = -MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	  
	  LeftWingBone1.rotateAngleZ = -par3 - 2.25F;
	  LeftWingBone2.rotateAngleZ = -par3;
	  LeftWingBone3.rotateAngleZ = -par3;
//	  LeftWingBone1.rotateAngleZ = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2 - 2.25F;
//	  LeftWingBone2.rotateAngleZ = -MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
//	  LeftWingBone3.rotateAngleZ = -MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	  
	  
	  
	  
	  
	  
	  
  }

}
