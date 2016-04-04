package alexndr.api.core;

import net.minecraft.creativetab.CreativeTabs;

public class ContentTypes 
{
	public static enum Block {
		GENERAL, ORE, MACHINE, OTHER;
	}
	
	public static enum Item {
		INGOT, TOOL, ARMOR, WEAPON, OTHER;
	}
	
	public static enum CreativeTab {
		GENERAL(CreativeTabs.tabBlock),
		BLOCKS(CreativeTabs.tabBlock), 
		MATERIALS(CreativeTabs.tabMaterials), 
		DECORATIONS(CreativeTabs.tabDecorations), 
		TOOLS(CreativeTabs.tabTools), 
		COMBAT(CreativeTabs.tabCombat), 
		OTHER(CreativeTabs.tabMisc);
		
		public final CreativeTabs vanillaTab;
		
		private CreativeTab(CreativeTabs vanillaTab) {
			this.vanillaTab = vanillaTab;
		}
	}
	

}
