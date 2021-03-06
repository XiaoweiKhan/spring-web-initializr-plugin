package ore.plugins.idea.swip.service;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import ore.plugins.idea.swip.service.base.SwipJavaCodeGenerator;
import ore.plugins.idea.swip.model.SwipRequest;

public class ResourcePersistableGenerator extends SwipJavaCodeGenerator {

    public ResourcePersistableGenerator(SwipRequest swipRequest) {
        super(swipRequest);
    }

    @Override
    public PsiClass generateJavaClass() {
        String resourcePersistableQualifiedName = String.format("ore.spring.web.initializr.domain.ResourcePersistable<%s>", swipRequest.getResourceIdQualifiedName());
        addQualifiedImplementsToClass(resourcePersistableQualifiedName, swipRequest.getResourceClass());
        swipRequest.getResourceClass().add(extractGetIdMethod());
        getJavaCodeStyleManager().shortenClassReferences(swipRequest.getResourceClass());
        return swipRequest.getResourceClass();
    }

    private PsiMethod extractGetIdMethod() {
        PsiMethod psiMethod = getElementFactory().createMethodFromText(String.format("public %s getResourcePersistableId() { return this.%s; }", swipRequest.getResourceIdQualifiedName(), swipRequest.getResourceIdPsiField().getNameIdentifier().getText()), swipRequest.getResourceClass());
        addOverrideTo(psiMethod);
        return psiMethod;
    }
}
