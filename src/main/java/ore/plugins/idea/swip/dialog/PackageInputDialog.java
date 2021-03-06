package ore.plugins.idea.swip.dialog;

import com.intellij.psi.PsiClass;
import ore.plugins.idea.lib.dialog.InputDialog;
import ore.plugins.idea.lib.exception.ValidationException;

public class PackageInputDialog extends InputDialog {
    private static final String JAVA_PACKAGE_PATTERN = "^[a-z][a-z0-9_]*(\\.[a-z0-9_]+)*[0-9a-z_]?$";

    public PackageInputDialog(PsiClass psiClass, String title, String componentText) {
        super(psiClass, title, componentText);
    }

    @Override
    public String getInput() {
        String packagePath = super.getInput();
        validatePackagePath(packagePath);
        return packagePath;
    }

    private void validatePackagePath(String packagePath) {
        if (packagePath.length() > 0 && !packagePath.toLowerCase().matches(JAVA_PACKAGE_PATTERN)) {
            throw new ValidationException("Invalid package name");
        }
    }
}
