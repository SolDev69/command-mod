package chatgptgen;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

public class GPTGeneratedMixinPlugin implements IMixinConfigPlugin {

	public static boolean HAS_REGION_INT_XYZ;

	// int x,y,z version (what you say b1.8+ has)
	public static final String DESC_INT_XYZ = "([DIIIIIIDDD)[D";

	@Override
	public void onLoad(String mixinPackage) {
		// nothing needed here
	}

	@Override public String getRefMapperConfig() { return null; }
	@Override public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}
	@Override public List<String> getMixins() { return null; }
	@Override public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
	@Override public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		// Only do detection for the mixin that targets the noise class
		if (mixinClassName.endsWith("NoiseMixinNew")) {

			// targetClassName is INTERNAL NAME like "net/minecraft/unmapped/C_12345"
			HAS_REGION_INT_XYZ = hasAnyMethodWithDesc(targetClassName, DESC_INT_XYZ);
			System.out.println("[Farlands] targetClassName=" + targetClassName
				+ " resourceExists=" + (getClass().getClassLoader().getResource(
				targetClassName.replace('.', '/') + ".class"
			) != null)
				+ " hasDesc=" + HAS_REGION_INT_XYZ);
			// Apply mixin only on versions that have the int signature
			return HAS_REGION_INT_XYZ;
		}
		return true;
	}

	private boolean hasAnyMethodWithDesc(String classNameDotOrSlash, String desc) {
		String internal = classNameDotOrSlash.replace('.', '/'); // IMPORTANT
		String resource = internal + ".class";

		try (InputStream in = getClass().getClassLoader().getResourceAsStream(resource)) {
			if (in == null) return false;

			ClassReader cr = new ClassReader(in);
			ClassNode cn = new ClassNode();
			cr.accept(cn, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);

			for (MethodNode mn : cn.methods) {
				if (mn.desc.equals(desc)) return true;
			}
			return false;
		} catch (Throwable t) {
			return false;
		}
	}
}
