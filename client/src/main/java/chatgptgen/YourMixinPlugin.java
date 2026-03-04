package chatgptgen;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

public class YourMixinPlugin implements IMixinConfigPlugin {
	private static final String TARGET_CLASS_DOT = "net.minecraft.world.gen.noise.PerlinNoise";

	private static final String NAME = "getRegion";
	private static final String DESC_INT_XYZ    = "([DIIIIIIDDD)[D"; // values + int,int,int + sizeX,Y,Z + scaleX,Y,Z
	private static final String DESC_DOUBLE_XYZ = "([DDDDIIIDDD)[D"; // values + double,double,double + sizeX,Y,Z + scaleX,Y,Z

	@Override public void onLoad(String mixinPackage) {}
	@Override public String getRefMapperConfig() { return null; }
	@Override public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}
	@Override public List<String> getMixins() { return null; }
	@Override public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
	@Override public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		if (mixinClassName.endsWith("NoiseMixinNew")) {
			return hasMethod(TARGET_CLASS_DOT, NAME, DESC_INT_XYZ);
		}
		return true;
	}

	private boolean hasMethod(String classDotName, String name, String desc) {
		String resource = classDotName.replace('.', '/') + ".class";
		try (InputStream in = getClass().getClassLoader().getResourceAsStream(resource)) {
			if (in == null) return false;

			ClassReader cr = new ClassReader(in);
			ClassNode cn = new ClassNode();
			cr.accept(cn, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);

			for (MethodNode mn : cn.methods) {
				if (mn.name.equals(name) && mn.desc.equals(desc)) return true;
			}
			return false;
		} catch (Throwable t) {
			return false;
		}
	}
}
