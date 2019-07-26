/**
 */
package fr.cea.nabla.ir.ir.impl;

import fr.cea.nabla.ir.ir.Connectivity;
import fr.cea.nabla.ir.ir.ConnectivityVariable;
import fr.cea.nabla.ir.ir.IrPackage;
import fr.cea.nabla.ir.ir.VarRef;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connectivity Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.cea.nabla.ir.ir.impl.ConnectivityVariableImpl#getDimensions <em>Dimensions</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.impl.ConnectivityVariableImpl#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.impl.ConnectivityVariableImpl#isPersist <em>Persist</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.impl.ConnectivityVariableImpl#isSparseMatrix <em>Sparse Matrix</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectivityVariableImpl extends VariableImpl implements ConnectivityVariable {
	/**
	 * The cached value of the '{@link #getDimensions() <em>Dimensions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDimensions()
	 * @generated
	 * @ordered
	 */
	protected EList<Connectivity> dimensions;

	/**
	 * The cached value of the '{@link #getDefaultValue() <em>Default Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected VarRef defaultValue;

	/**
	 * The default value of the '{@link #isPersist() <em>Persist</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPersist()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PERSIST_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPersist() <em>Persist</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPersist()
	 * @generated
	 * @ordered
	 */
	protected boolean persist = PERSIST_EDEFAULT;

	/**
	 * The default value of the '{@link #isSparseMatrix() <em>Sparse Matrix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSparseMatrix()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SPARSE_MATRIX_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSparseMatrix() <em>Sparse Matrix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSparseMatrix()
	 * @generated
	 * @ordered
	 */
	protected boolean sparseMatrix = SPARSE_MATRIX_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectivityVariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.CONNECTIVITY_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT FIXME workaround BUG 89325
	 */
	@SuppressWarnings("serial")
	public EList<Connectivity> getDimensions() {
		if (dimensions == null) {
			dimensions = new EObjectResolvingEList<Connectivity>(Connectivity.class, this, IrPackage.CONNECTIVITY_VARIABLE__DIMENSIONS) {
				@Override
	    		protected boolean isUnique() { return false; }
			};
		}
		return dimensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarRef getDefaultValue() {
		if (defaultValue != null && defaultValue.eIsProxy()) {
			InternalEObject oldDefaultValue = (InternalEObject)defaultValue;
			defaultValue = (VarRef)eResolveProxy(oldDefaultValue);
			if (defaultValue != oldDefaultValue) {
				InternalEObject newDefaultValue = (InternalEObject)defaultValue;
				NotificationChain msgs = oldDefaultValue.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IrPackage.CONNECTIVITY_VARIABLE__DEFAULT_VALUE, null, null);
				if (newDefaultValue.eInternalContainer() == null) {
					msgs = newDefaultValue.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IrPackage.CONNECTIVITY_VARIABLE__DEFAULT_VALUE, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.CONNECTIVITY_VARIABLE__DEFAULT_VALUE, oldDefaultValue, defaultValue));
			}
		}
		return defaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarRef basicGetDefaultValue() {
		return defaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefaultValue(VarRef newDefaultValue, NotificationChain msgs) {
		VarRef oldDefaultValue = defaultValue;
		defaultValue = newDefaultValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IrPackage.CONNECTIVITY_VARIABLE__DEFAULT_VALUE, oldDefaultValue, newDefaultValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultValue(VarRef newDefaultValue) {
		if (newDefaultValue != defaultValue) {
			NotificationChain msgs = null;
			if (defaultValue != null)
				msgs = ((InternalEObject)defaultValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IrPackage.CONNECTIVITY_VARIABLE__DEFAULT_VALUE, null, msgs);
			if (newDefaultValue != null)
				msgs = ((InternalEObject)newDefaultValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IrPackage.CONNECTIVITY_VARIABLE__DEFAULT_VALUE, null, msgs);
			msgs = basicSetDefaultValue(newDefaultValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.CONNECTIVITY_VARIABLE__DEFAULT_VALUE, newDefaultValue, newDefaultValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPersist() {
		return persist;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPersist(boolean newPersist) {
		boolean oldPersist = persist;
		persist = newPersist;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.CONNECTIVITY_VARIABLE__PERSIST, oldPersist, persist));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSparseMatrix() {
		return sparseMatrix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSparseMatrix(boolean newSparseMatrix) {
		boolean oldSparseMatrix = sparseMatrix;
		sparseMatrix = newSparseMatrix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.CONNECTIVITY_VARIABLE__SPARSE_MATRIX, oldSparseMatrix, sparseMatrix));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IrPackage.CONNECTIVITY_VARIABLE__DEFAULT_VALUE:
				return basicSetDefaultValue(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.CONNECTIVITY_VARIABLE__DIMENSIONS:
				return getDimensions();
			case IrPackage.CONNECTIVITY_VARIABLE__DEFAULT_VALUE:
				if (resolve) return getDefaultValue();
				return basicGetDefaultValue();
			case IrPackage.CONNECTIVITY_VARIABLE__PERSIST:
				return isPersist();
			case IrPackage.CONNECTIVITY_VARIABLE__SPARSE_MATRIX:
				return isSparseMatrix();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IrPackage.CONNECTIVITY_VARIABLE__DIMENSIONS:
				getDimensions().clear();
				getDimensions().addAll((Collection<? extends Connectivity>)newValue);
				return;
			case IrPackage.CONNECTIVITY_VARIABLE__DEFAULT_VALUE:
				setDefaultValue((VarRef)newValue);
				return;
			case IrPackage.CONNECTIVITY_VARIABLE__PERSIST:
				setPersist((Boolean)newValue);
				return;
			case IrPackage.CONNECTIVITY_VARIABLE__SPARSE_MATRIX:
				setSparseMatrix((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case IrPackage.CONNECTIVITY_VARIABLE__DIMENSIONS:
				getDimensions().clear();
				return;
			case IrPackage.CONNECTIVITY_VARIABLE__DEFAULT_VALUE:
				setDefaultValue((VarRef)null);
				return;
			case IrPackage.CONNECTIVITY_VARIABLE__PERSIST:
				setPersist(PERSIST_EDEFAULT);
				return;
			case IrPackage.CONNECTIVITY_VARIABLE__SPARSE_MATRIX:
				setSparseMatrix(SPARSE_MATRIX_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case IrPackage.CONNECTIVITY_VARIABLE__DIMENSIONS:
				return dimensions != null && !dimensions.isEmpty();
			case IrPackage.CONNECTIVITY_VARIABLE__DEFAULT_VALUE:
				return defaultValue != null;
			case IrPackage.CONNECTIVITY_VARIABLE__PERSIST:
				return persist != PERSIST_EDEFAULT;
			case IrPackage.CONNECTIVITY_VARIABLE__SPARSE_MATRIX:
				return sparseMatrix != SPARSE_MATRIX_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (persist: ");
		result.append(persist);
		result.append(", sparseMatrix: ");
		result.append(sparseMatrix);
		result.append(')');
		return result.toString();
	}

} //ConnectivityVariableImpl
